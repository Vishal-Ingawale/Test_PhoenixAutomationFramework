package com.api.tests;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserDetailsAPITestOptimized {

    @Test(description = "Verify if the userdetails api response is shown correctly", groups={"api","smoke","regression"})
    public void userDetailsAPITest() throws IOException {
        given()
                .spec(SpecUtil.requestSpecWithAuth(Role.FD))
                .when()
                .get("userdetails")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
    }
}
