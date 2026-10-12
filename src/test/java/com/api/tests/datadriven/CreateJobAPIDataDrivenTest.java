package com.api.tests.datadriven;

import com.api.constant.Role;
import com.api.model.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateJobAPIDataDrivenTest {

    private CreateJobPayload createJobPayload;

    @Test(description = "Verify if the create job api is able to create inwarranty job", groups = {"api", "smoke", "regression"},
    dataProviderClass = com.dataproviders.DataProviderUtils.class,
    dataProvider = "CreateJobAPIDataProvider")
    public void createJobAPITest(CreateJobPayload createJobPayload) {
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
