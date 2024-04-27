package com.rest;

import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestAPI {




    @Test
    public void test(){
        RestAssured.config = RestAssuredConfig.newConfig().sslConfig(SSLConfig.sslConfig().allowAllHostnames().relaxedHTTPSValidation());
        baseURI = "https://api.postman.com";
        String apiKey = "PMAK-661a536fb49c1e0001154e83-dc1f8a67515c9950da6cb73e7691580a5b";

        Response response = RestAssured.given()
                .header("X-API-Key",apiKey)
                .contentType("application/json").get("/workspaces");

        System.out.println(response.getBody().asString());

        System.out.println();

        System.out.println("Status Code "+ response.getStatusCode());
    }
}
