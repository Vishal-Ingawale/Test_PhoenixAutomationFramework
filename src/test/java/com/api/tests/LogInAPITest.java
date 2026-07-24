package com.api.tests;

import com.api.pojo.UserCredentials;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class LogInAPITest {

    @Test
    public void loginAPITest(){
        UserCredentials userCredentials = new UserCredentials("iamfd","password");

        given()
                .baseUri("http://64.227.160.186:9000/v1")
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
                .body("message",Matchers.equalTo("Success"))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LogInResponseSchema.json"));

    }
}
