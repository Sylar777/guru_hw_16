import models.lombok.*;
import models.pojo.RequestRegistrationBodyPojoModel;
import models.pojo.ResponseRegistrationBodyPojoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.RegistrationSpec.*;
import static specs.SingleResourceSpec.getSingleResourceRequestSpecification;
import static specs.SingleResourceSpec.successGetSingleResourceResponseSpecification;
import static specs.UsersSpec.getUsersRequestSpecification;
import static specs.UsersSpec.successGetUsersResponseSpecification;

public class RegressApiTests extends TestBase {
    @Test
    @DisplayName("Successful registration (with Pojo)")
    void registrationSuccessfulPojoTest() {
        RequestRegistrationBodyPojoModel requestRegistrationBodyPojoModel = new RequestRegistrationBodyPojoModel();
        requestRegistrationBodyPojoModel.setEmail("rachel.howell@reqres.in");
        requestRegistrationBodyPojoModel.setPassword("qwerty");

        var responseRegistrationBodyPojoModel = step("Make request", () -> {
            return given(registrationRequestSpecification)
                    .body(requestRegistrationBodyPojoModel)
                    .when()
                    .post("/register")
                    .then()
                    .spec(successRegistrationResponseSpecification)
                    .extract().as(ResponseRegistrationBodyPojoModel.class);
        });

        step("Check response", () -> {
            assertNotNull(responseRegistrationBodyPojoModel);
        });
    }

    @Test
    @DisplayName("Successful registration (with Lombok)")
    void registrationSuccessfulLombokTest() {
        RequestRegistrationBodyLombokModel requestRegistrationBodyLombokModel = new RequestRegistrationBodyLombokModel();
        requestRegistrationBodyLombokModel.setEmail("rachel.howell@reqres.in");
        requestRegistrationBodyLombokModel.setPassword("qwerty");


        var responseRegistrationBodyLombokModel = step("Make request", () -> {
            return given(registrationRequestSpecification)
                    .body(requestRegistrationBodyLombokModel)
                    .when()
                    .post("/register")
                    .then()
                    .spec(successRegistrationResponseSpecification)
                    .extract().as(ResponseRegistrationBodyLombokModel.class);
        });

        step("Check response", () -> {
            assertNotNull(responseRegistrationBodyLombokModel);
        });
    }

    @Test
    @DisplayName("Incorrect user registration (with Pojo)")
    void registrationIncorrectUserPojoTest() {
        RequestRegistrationBodyPojoModel requestRegistrationBodyPojoModel = new RequestRegistrationBodyPojoModel();
        requestRegistrationBodyPojoModel.setEmail("fake@fake.in");
        requestRegistrationBodyPojoModel.setPassword("qwerty");

        var responseRegistrationBodyPojoModel = step("Make request", () -> {
            return given(registrationRequestSpecification)
                    .body(requestRegistrationBodyPojoModel)
                    .when()
                    .post("/register")
                    .then()
                    .spec(unsuccessRegistrationResponseSpecification)
                    .extract().as(ResponseRegistrationBodyPojoModel.class);
        });

        step("Check response", () -> {
            assertEquals("Note: Only defined users succeed registration", responseRegistrationBodyPojoModel.getError());
        });
    }

    @Test
    @DisplayName("Incorrect user registration (with Lombok)")
    void registrationIncorrectUserLombokTest() {
        RequestRegistrationBodyLombokModel requestRegistrationBodyLombokModel = new RequestRegistrationBodyLombokModel();
        requestRegistrationBodyLombokModel.setEmail("fake@fake.in");
        requestRegistrationBodyLombokModel.setPassword("qwerty");

        var responseRegistrationBodyLombokModel = step("Make request", () -> {
            return given(registrationRequestSpecification)
                    .body(requestRegistrationBodyLombokModel)
                    .when()
                    .post("/register")
                    .then()
                    .spec(unsuccessRegistrationResponseSpecification)
                    .extract().as(ResponseRegistrationBodyLombokModel.class);
        });

        step("Check response", () -> {
            assertEquals("Note: Only defined users succeed registration", responseRegistrationBodyLombokModel.getError());
        });
    }

    @Test
    @DisplayName("Registration without password (with Pojo)")
    void registrationUserWithOutPasswordPojoTest() {
        RequestRegistrationBodyPojoModel requestRegistrationBodyPojoModel = new RequestRegistrationBodyPojoModel();
        requestRegistrationBodyPojoModel.setEmail("rachel.howell@reqres.in");

        var responseRegistrationBodyPojoModel = step("Make request", () -> {
            return given(registrationRequestSpecification)
                    .body(requestRegistrationBodyPojoModel)
                    .when()
                    .post("/register")
                    .then()
                    .spec(unsuccessRegistrationResponseSpecification)
                    .extract().as(ResponseRegistrationBodyPojoModel.class);
        });

        step("Check response", () -> {
            assertEquals("Missing password", responseRegistrationBodyPojoModel.getError());
        });
    }

    @Test
    @DisplayName("Registration without password (with Lombok)")
    void registrationUserWithOutPasswordLombokTest() {
        RequestRegistrationBodyLombokModel requestRegistrationBodyLombokModel = new RequestRegistrationBodyLombokModel();
        requestRegistrationBodyLombokModel.setEmail("rachel.howell@reqres.in");

        var responseRegistrationBodyLombokModel = step("Make request", () -> {
            return given(registrationRequestSpecification)
                    .body(requestRegistrationBodyLombokModel)
                    .when()
                    .post("/register")
                    .then()
                    .spec(unsuccessRegistrationResponseSpecification)
                    .extract().as(ResponseRegistrationBodyLombokModel.class);
        });

        step("Check response", () -> {
            assertEquals("Missing password", responseRegistrationBodyLombokModel.getError());
        });
    }

    @Test
    @DisplayName("List of users contains six users (with Lombok)")
    void listOfUsersContainsSixUsersTest() {
        ResponseListOfUsersLombokModel responseListOfUsersLombokModel = step("Make request", () -> {
            return given(getUsersRequestSpecification)
                    .when()
                    .get("/users?page=2")
                    .then()
                    .spec(successGetUsersResponseSpecification)
                    .extract().as(ResponseListOfUsersLombokModel.class);
        });

        step("Check response", () -> {
            assertNotNull(responseListOfUsersLombokModel);
            assertEquals(6, responseListOfUsersLombokModel.getData().length);
        });
    }

    @Test
    @DisplayName("Get single user successful (with Lombok)")
    void getSingleResourceSuccessfulTest() {
        var responseSingleResourceLombokModel = step("Make request", () -> {
                    return given(getSingleResourceRequestSpecification)
                            .when()
                            .get("/unknown")
                            .then()
                            .spec(successGetSingleResourceResponseSpecification)
                            .extract().as(ResponseSingleResourceLombokModel.class);
                });

        step("Check response", () -> {
            assertNotNull(responseSingleResourceLombokModel);
        });
    }
}
