package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.Users;
import com.qa.api.util.JsonUtils;
import com.qa.api.util.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAUserWithDeserilaztionTest extends BaseTest {
	private String tokenID;
	@BeforeClass
	public void setUpToken() {
		tokenID="bb75cdea16d07860e6c40952b1c4f8aa0e0be3c14d5d4cf4863a0aad13b61b7c";
	   ConfigManager.set("bearertoken", tokenID);
	}
	
	@Test
	
	public void GetAUser() {
		
		//users user=new users("ravi", "male", StringUtils.generateRandomMailID(), "active");
				Users user=Users.builder()
		     		   .name("ravi")
		     		   .email(StringUtils.generateRandomMailID())
		     		   .gender("male")
		     		   .status("active").build();
				
				
			Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINTS, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		    Users usersResponse=	JsonUtils.deserialize(response, Users.class);
		    
		    Assert.assertEquals(usersResponse.getName(), user.getName());
		    Assert.assertNotNull(usersResponse.getId());

		
	}
	
	

}
