package com.api.tests;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.ConfigManagerOld;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class LogInAPITest {

    @Test
    public void loginAPITest() throws IOException {
        UserCredentials userCredentials = new UserCredentials("iamfd", "password");

        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .contentType(ContentType.JSON)
                .and()
                .accept(ContentType.JSON)
                .and()
                .body(userCredentials)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()
                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(200)
                .time(Matchers.lessThan(1000L))
                .and()
                .body("message", Matchers.equalTo("Success"))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LogInResponseSchema.json"));

    }
}
