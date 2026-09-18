package com.api.tests;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CountAPITestOptimized {

    @Test(description = "Verify if the count api is giving correct response", groups={"api","smoke","regression"})
    public void verifyCountAPIResponse(){
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", AuthTokenProvider.getToken(Role.FD))
                .when()
                .get("/dashboard/count")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", Matchers.equalTo("Success"))
                .time(Matchers.lessThan(1000L))
                .body("data",Matchers.notNullValue())
                .body("data.size()",Matchers.equalTo(3))
                .body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
                .body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
    }

    @Test(description = "\"Verify if count api is giving correct status code for invalid token", groups={"api","negative","smoke","regression"})
    public void countAPITest_MissingAuthToken(){
        given()
                .spec(SpecUtil.requestSpec())
                .when()
                .get("/dashboard/count")
                .then()
                .spec(SpecUtil.responseSpec_TEXT(401));
    }
}
