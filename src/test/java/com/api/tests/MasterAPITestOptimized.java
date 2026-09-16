package com.api.tests;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class MasterAPITestOptimized {

    @Test
    public void masterAPITest(){
        RestAssured.given()
                .spec(SpecUtil.requestSpecWithAuth(Role.FD))
                .when()
                .post("master")   //default content-type application/url-formencoded
                .then()
                .spec(SpecUtil.responseSpec_OK())
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
                .spec(SpecUtil.requestSpec())
                .log().all()
                .when()
                .post("master")   //default content-type application/url-formencoded
                .then()
                .spec(SpecUtil.responseSpec_TEXT(401));
    }
}
