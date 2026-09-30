package com.api.tests.datadriven;

import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class LoginAPIDataDrivenTest {

    @Test(description = "Verifying if login api is working for FD user",
            groups = {"api", "regression", "datadriven"},
            dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "LoginAPIDataProvider"
            )
    public void loginAPITest(UserBean userbean) {

        given()
                .spec(SpecUtil.requestSpec(userbean))
                .when()
                .post("login")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", Matchers.equalTo("Success"))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LogInResponseSchema.json"));

    }
}
