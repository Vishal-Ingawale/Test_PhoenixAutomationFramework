package com.api.utils;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.*;

public class AuthTokenProvider {
    private AuthTokenProvider(){
    }

    public static String getToken(Role role){
        UserCredentials userCredentials = null;
        if (role == Role.FD){
            userCredentials = new UserCredentials("iamfd","password");
        } else if (role == Role.SUP) {
            userCredentials = new UserCredentials("iamsup","password");
        } else if (role == Role.ENG) {
            userCredentials = new UserCredentials("iameng","password");
        } else if (role == Role.QC) {
            userCredentials = new UserCredentials("iamqc","password");
        }

        String token =
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .contentType(ContentType.JSON)
                .body(userCredentials)
                .when()
                .post("login")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .body("message", Matchers.equalTo("Success"))
                .extract()
                .body()
                .jsonPath()
                .getString("data.token");
        System.out.println(token);
        return token;
    }
}
