package com.qa.api.mocking.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;
import com.qa.api.pojo.Users;
import com.qa.api.util.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockCreateUserAPITest extends BaseTest{
	
	
	@Test
	
	public void createFakeUserTest() {
	APIMocks.defineCreateUser();
	
	Users user=Users.builder()
  		   .name("ravi")
  		   .email(StringUtils.generateRandomMailID())
  		   .gender("male")
  		   .status("active").build();
	
//String fakeJsonBody="{\r\n"
//		+ "    \"id\": 101,\r\n"
//		+ "    \"name\": \"Ravi Kumar\",\r\n"
//		+ "    \"address\": \"42 MG Road, Sector 18\",\r\n"
//		+ "    \"city\": \"Bangalore\"\r\n"
//		+ "  }";
//		
	
        Response response=	restClient.post(BASE_URL_MOCKSERVER, MOCKSERVER_ENDPOINTS, user, null, null, AuthType.NO_AUTH, ContentType.JSON);
        
        
        response.then().assertThat().statusCode(201);
	}

}
