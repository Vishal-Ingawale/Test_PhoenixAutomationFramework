package com.api.tests;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class MasterAPITest {

    @Test
    public void masterAPITest(){
        RestAssured.given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", AuthTokenProvider.getToken(Role.FD))
                .and()
                .contentType("")
                .log().all()
                .when()
                .post("master")   //default content-type application/url-formencoded
                .then()
                .log().all()
                .statusCode(200)
                .time(Matchers.lessThan(1000L))
                .body("message",Matchers.equalTo("Success"))
                .body("data",Matchers.notNullValue())
                .body("data",hasKey("mst_oem"))
                .body("data",hasKey("mst_model"))
                .body("$",hasKey("message"))
                .body("$",hasKey("data"))
                .body("data.mst_oem.size()",equalTo(2))
                .body("data.mst_oem.id",everyItem(notNullValue()))
                .body("data.mst_oem.name",everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
    }

    @Test
    public void invalidTokenMasterAPITest(){
        RestAssured.given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", "")
                .and()
                .contentType("")
                .log().all()
                .when()
                .post("master")   //default content-type application/url-formencoded
                .then()
                .log().all()
                .statusCode(401);
    }
}
