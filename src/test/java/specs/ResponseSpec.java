package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;

public class ResponseSpec {
    public static ResponseSpecification successResponse200Specification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification unsuccessResponse400Specification = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();
}
