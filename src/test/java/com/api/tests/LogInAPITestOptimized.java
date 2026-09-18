package com.api.tests;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class LogInAPITestOptimized {

    private UserCredentials userCredentials;

    @BeforeMethod(description = "Create the payload for the Login api")
    public void setup() {
        userCredentials = new UserCredentials("iamfd", "password");
    }

    @Test(description = "Verifying if login api is working for FD user", groups = {"api", "regression", "smoke"})
    public void loginAPITest() throws IOException {

        given()
                .spec(SpecUtil.requestSpec(userCredentials))
                .when()
                .post("login")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", Matchers.equalTo("Success"))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LogInResponseSchema.json"));

    }
}
