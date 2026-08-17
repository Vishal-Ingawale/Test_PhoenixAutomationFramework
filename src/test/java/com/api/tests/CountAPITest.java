package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CountAPITest {

    @Test
    public void verifyCountAPIResponse(){
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", AuthTokenProvider.getToken(Role.FD))
                .when()
                .get("/dashboard/count")
                .then()
                .log().all()
                .statusCode(200)
                .body("message", Matchers.equalTo("Success"))
                .time(Matchers.lessThan(1000L))
                .body("data",Matchers.notNullValue())
                .body("data.size()",Matchers.equalTo(3))
                .body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
                .body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
    }

    @Test
    public void countAPITest_MissingAuthToken(){
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .when()
                .get("/dashboard/count")
                .then()
                .log().all()
                .statusCode(401);
    }
}
