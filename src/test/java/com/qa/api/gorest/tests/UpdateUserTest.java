package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Users;
import com.qa.api.util.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest {
	
	@Test
	public void updateUsertest() {
		//Create a User
		Users user=new Users(null,"ravi", "male", StringUtils.generateRandomMailID(), "active");
		Response responsePost=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	    Assert.assertEquals(responsePost.jsonPath().getString("name"),"ravi");
	    Assert.assertNotNull(responsePost.jsonPath().getString("id"));
	    String userId=responsePost.jsonPath().getString("id");
	    
	    //Get a User
		Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		Assert.assertEquals(responseGet.jsonPath().getString("id"),userId);
		
		//Update a User
		user.setName("raviAuto");
		user.setStatus("inactive");
		
		Response responsePut = restClient.put(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, user, null,null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responsePut.statusLine().contains("OK"));
		Assert.assertEquals(responsePut.jsonPath().getString("id"),userId);
		Assert.assertEquals(responsePut.jsonPath().getString("name"),"raviAuto");
		Assert.assertEquals(responsePut.jsonPath().getString("status"),"inactive");
		
		//Get a User
		responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		Assert.assertEquals(responseGet.jsonPath().getString("id"),userId);
		Assert.assertEquals(responsePut.jsonPath().getString("name"),"raviAuto");
		Assert.assertEquals(responsePut.jsonPath().getString("status"),"inactive");
	}

}
