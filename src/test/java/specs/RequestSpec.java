package specs;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class RequestSpec {
    public static RequestSpecification registrationRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(ContentType.JSON);

    public static RequestSpecification getSingleResourceRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().all();

    public static RequestSpecification getUsersRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().all();
}
