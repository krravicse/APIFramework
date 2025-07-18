package com.qa.api.mocking.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockGetuserAPITest extends BaseTest{
	
	@Test
	
	public void getDummyUserAPITest() {
		APIMocks.defineGetUserMock();
		
	Response response=	restClient.get(BASE_URL_MOCKSERVER, MOCKSERVER_ENDPOINTS, null, null, AuthType.NO_AUTH, ContentType.ANY);
	response.prettyPrint();
	
	response.then().assertThat().statusCode(200);
	
	System.out.println(response.asString());
	}

	@Test
	
	public void getDummyUserAPITestWithJsonFile() {
		APIMocks.defineGetUserMockwithJsonFile();

		Response response = restClient.get(BASE_URL_MOCKSERVER, MOCKSERVER_ENDPOINTS, null, null, AuthType.NO_AUTH,
				ContentType.ANY);
		response.prettyPrint();

		response.then().assertThat().statusCode(200);

		System.out.println(response.asString());
	}
	
	
@Test
	
	public void getDummyUserAPITestWithQueryParams() {
		APIMocks.defineGetUserMockWithQueryParams();
		
		Map<String, String>userQuery=Map.of("name","ravi");
		

		Response response = restClient.get(BASE_URL_MOCKSERVER, MOCKSERVER_ENDPOINTS, userQuery, null, AuthType.NO_AUTH,
				ContentType.ANY);
		response.then().assertThat().statusCode(200);

		System.out.println(response.jsonPath().getString("name"));
	}

}
