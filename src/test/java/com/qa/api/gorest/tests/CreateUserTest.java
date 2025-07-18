package com.qa.api.gorest.tests;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Users;
import com.qa.api.util.StringUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Epic("Epic 100: Go Rest Get User API Feature")
@Story("US 100: feature go rest api - get user api")
public class CreateUserTest extends BaseTest{
	
	private String tokenID;
	@BeforeClass
	public void setUpToken() {
		tokenID="bb75cdea16d07860e6c40952b1c4f8aa0e0be3c14d5d4cf4863a0aad13b61b7c";
	   ConfigManager.set("bearertoken", tokenID);
	}
	@Test
	
	
	@Description("getting all the users...")
	@Owner("ravi")
	@Severity(SeverityLevel.CRITICAL)
	public void createaSpecificUserByLombok() {
		
		//users user=new users("ravi", "male", StringUtils.generateRandomMailID(), "active");
		Users user=Users.builder()
     		   .name("ravi")
     		   .email(StringUtils.generateRandomMailID())
     		   .gender("male")
     		   .status("active").build();
		
		
	Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
    Assert.assertEquals(response.jsonPath().getString("name"),"ravi");
    Assert.assertNotNull(response.jsonPath().getString("id"));	
	}
	
    @Test
	
	public void createaSpecificUserByjsonFile() throws IOException {
    	
    	String rawJson=new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/User.json")));
		String updatedjson=rawJson.replace("{{emailID}}", StringUtils.generateRandomMailID());		
		
	Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, updatedjson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
    Assert.assertEquals(response.jsonPath().getString("name"),"seema");
    Assert.assertNotNull(response.jsonPath().getString("id"));
    }  

}
