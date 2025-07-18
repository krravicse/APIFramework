package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	@Test
	public void getAllUsers() {
	Response response=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	@Test
	public void getAllUsersWithQueryParamtest() {
		Map<String,String> queryparams=new HashMap<String, String>();
		queryparams.put("name", "naveen");
		queryparams.put("status", "inactive");
		Response response=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, queryparams, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));	
		
	}
	
	@Test
	public void getSingleUserTest() {
		String userId = "7725337";
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
		Assert.assertEquals(response.jsonPath().getString("id"),userId);
		

	}
}
