package com.api.tests.datadriven;

import com.api.constant.Role;
import com.api.model.CreateJobPayload;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.startsWith;

public class CreateJobAPIExcelDataDrivenTest {

    private CreateJobPayload createJobPayload;

    @Test(description = "Verify if the create job api is able to create inwarranty job", groups = {"api", "smoke", "regression"},
    dataProviderClass = com.dataproviders.DataProviderUtils.class,
    dataProvider = "CreateJobAPIExcelDataProvider")
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
                .spec(responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
                .body("message",equalTo("Job created successfully. "))
                .body("data.mst_service_location_id",equalTo(1))
                .body("data.job_number",startsWith("JOB_"))
                .statusCode(200);
    }

}
