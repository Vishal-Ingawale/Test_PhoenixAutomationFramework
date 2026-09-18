package com.api.tests;

import com.api.constant.Role;
import com.api.pojo.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateJobAPITest {

    private CreateJobPayload createJobPayload;

    @BeforeMethod(description = "Creating create job api request payload")
    public void setup() {
        Customer customer = new Customer("Vishal", "Ingawale", "9665431543", "", "vishal96ingawale@gmail.com", "");
        CustomerAddress customerAddress = new CustomerAddress("F:5", "Moraya Krupa", "Sudarshan Nagar", "Chinchwad", "Pune", "411033", "India", "Maharashtra");
        CustomerProduct customerProduct = new CustomerProduct("2025-11-24T18:30:00.000Z", "19991432448429", "19991432448429", "19991432448429", "2025-11-24T18:30:00.000Z", 1, 1);
        Problems problems = new Problems(1, "Battery Issue");
        Problems[] problemsArray = new Problems[1];
        problemsArray[0] = problems;
        createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
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
