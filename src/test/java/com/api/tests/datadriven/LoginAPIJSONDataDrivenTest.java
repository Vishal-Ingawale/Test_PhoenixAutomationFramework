package com.api.tests.datadriven;

import com.api.model.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginAPIJSONDataDrivenTest {

    @Test(description = "Verifying if login api is working for FD user",
            groups = {"api", "regression", "datadriven"},
            dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "LoginAPIJsonDataProvider"
            )
    public void loginAPITest(UserCredentials userCredentials) {

        given()
                .spec(SpecUtil.requestSpec(userCredentials))
                .when()
                .post("login")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", Matchers.equalTo("Success"))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LogInResponseSchema.json"));

    }
}
