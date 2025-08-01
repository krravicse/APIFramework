package com.qa.api.schema.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Users;
import com.qa.api.util.SchemaValidator;
import com.qa.api.util.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestUserAPISchemaTest extends BaseTest{
	
	
	
	@Test
	public void getUsersAPISchemaTest() {	
		
		
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	
		Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/getuserschema.json"));
	
	}
	
	
	@Test
	public void createUserAPISchemaTest() {	
		ConfigManager.set("bearertoken", "e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
		
		Users user=Users.builder()
	     		   .name("ravi")
	     		   .email(StringUtils.generateRandomMailID())
	     		   .gender("male")
	     		   .status("active").build();
			
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
			
		Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/createuserschema.json"));
	
	}
	
	

}
