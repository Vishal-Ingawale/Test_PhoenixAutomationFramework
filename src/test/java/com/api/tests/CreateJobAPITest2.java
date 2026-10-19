package com.api.tests;

import com.api.constant.Role;
import com.api.model.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.DateTimeUtil;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class CreateJobAPITest2 {

    private CreateJobPayload createJobPayload;

    @BeforeMethod(description = "Creating create job api request payload")
    public void setup() {
        Faker faker = new Faker(new Locale("en-IND"));

        String fname = faker.name().firstName();
        String lname = faker.name().lastName();
        String mobileNumber = faker.numerify("96########");
        String alternateMobileNumber = faker.numerify("96########");
        String customerEmailAddress = faker.internet().emailAddress();
        String altCustomerEmailAddress = faker.internet().emailAddress();

        Customer customer = new Customer(fname,lname,mobileNumber,alternateMobileNumber,customerEmailAddress,altCustomerEmailAddress);
        System.out.println(customer);

        String flatNumber = faker.numerify("###");
        String apartmentName = faker.address().streetName();
        String streetName = faker.address().streetName();
        String landmark = faker.address().streetName();
        String area = faker.address().streetName();
        String pinCode = faker.numerify("#####");
        String country = faker.address().country();
        String state = faker.address().state();

        CustomerAddress customerAddress = new CustomerAddress(flatNumber,apartmentName,streetName,landmark,area,pinCode,country,state);
        System.out.println(customerAddress);

        //CustomerProduct Fake Object
        String dop = DateTimeUtil.getTimeWithDaysAgo(10);
        String imeiSerialNumber = faker.numerify("###############");
        String popUrl = faker.internet().url();
        CustomerProduct customerProduct = new CustomerProduct(dop,imeiSerialNumber,imeiSerialNumber,imeiSerialNumber,popUrl,1,1);
        System.out.println(customerProduct);

        String fakeRemark = faker.lorem().sentence(5);

        //I want to generate random number between 1 to 27
        Random random = new Random();
        int problemId = random.nextInt(26)+1;

        Problems problems = new Problems(problemId,fakeRemark);
        System.out.println(problems);

        List<Problems> problemsList = new ArrayList<Problems>();
        problemsList.add(problems);

        createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
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
