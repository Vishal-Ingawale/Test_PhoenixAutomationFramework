package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest() throws IOException {
        Header authHeader = new Header("Authorization", AuthTokenProvider.getToken(Role.SUP));
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header(authHeader)
                .and()
                .accept(ContentType.JSON)
                .log().uri()
                .log().method()
                .log().body()
                .log().headers()
                .when()
                .get("userdetails")
                .then()
                .log().all()
                .statusCode(200)
                .time(Matchers.lessThan(1000L))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
    }
}
