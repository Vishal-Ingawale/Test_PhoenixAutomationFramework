package com.api.tests;

import com.api.constant.Role;
import com.api.model.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.FakerDataGenerator;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateJobAPITestwithFakerData {

    private CreateJobPayload createJobPayload;

    @BeforeMethod(description = "Creating create job api request payload")
    public void setup() {

        createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
    }

    @Test(description = "Verify if the create job api is able to create inwarranty job", groups = {"api", "smoke", "regression"})
    public void createJobAPITest() {
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", AuthTokenProvider.getToken(Role.FD))
                .contentType(ContentType.JSON)
                .body(createJobPayload)
                .log().all()
                .when()
                .post("/job/create")
                .then()
                .log().all()
                .statusCode(200);
    }
}
