package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Users;
import com.qa.api.util.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {
	
	@Test
	
	public void deleteUsertest() {
		
	//Create a user
	Users user=Users.builder()
  		   .name("ravi")
  		   .email(StringUtils.generateRandomMailID())
  		   .gender("male")
  		   .status("active").build();	
   Response responsePost=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
   Assert.assertEquals(responsePost.jsonPath().getString("name"),"ravi");
   Assert.assertNotNull(responsePost.jsonPath().getString("id"));	
   
   String userId=responsePost.jsonPath().getString("id");
   
   //Get a User
	Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, null, null,
			AuthType.BEARER_TOKEN, ContentType.JSON);
	Assert.assertTrue(responseGet.statusLine().contains("OK"));
	Assert.assertEquals(responseGet.jsonPath().getString("id"),userId);
	
	//Delete a User with same user id
	Response responseDelete=	restClient.delete(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, null, null,
			AuthType.BEARER_TOKEN, ContentType.JSON);
	Assert.assertTrue(responseDelete.statusLine().contains("No Content"));
	
	//Get:fetch a deleted user id with Same user id
	
	responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS + "/" + userId, null, null,
			AuthType.BEARER_TOKEN, ContentType.JSON);
	
	Assert.assertTrue(responseGet.statusLine().contains("Not Found"));
	Assert.assertEquals(responseGet.jsonPath().getString("message"),"Resource not found");
	Assert.assertEquals(responseGet.statusCode(),404);
	
   
   
   
 
 
	}

}
