package com.rest.postman;

import static io.restassured.RestAssured.baseURI;

import java.util.Properties;

import org.testng.annotations.Test;

import commonFunctions.Base;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class GetPostManWorkSpace extends Base {

	Properties data = loadProperties(GetPostManWorkSpace.class.getPackageName());

	@Test
	public void getPostmanWorkspace() {
		baseURI = data.getProperty("baseURI");
		RestAssured.config = RestAssuredConfig.newConfig()
				.sslConfig(SSLConfig.sslConfig().allowAllHostnames().relaxedHTTPSValidation());

		Response response = RestAssured.given().header("X-API-Key", data.getProperty("apiKey")).contentType("application/json").get("/workspaces");

		System.out.println(response.getBody().asString());

		System.out.println("Status Code " + response.getStatusCode());
	}

	@Test
	public void printEnvironmentVariables(){
		loadEnvironmentVariables("POSTMAN");
	}
}
