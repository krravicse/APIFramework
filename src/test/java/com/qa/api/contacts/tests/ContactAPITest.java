package com.qa.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactAPITest extends BaseTest {
	private String tokenId;
	@BeforeMethod
	
	public void getToken() {
		ContactCredentials cred=	ContactCredentials.builder()
		.email("naveenanimation20@gmail.com")
		.password("test@123")
		.build();
		
		
	Response response=	restClient.post(BASE_URL_CONTACTS, CONTACTS_LOGIN_ENDPOINTS, cred, null, null, AuthType.NO_AUTH, ContentType.JSON);
	Assert.assertEquals(response.getStatusCode(), 200);
	tokenId=response.jsonPath().getString("token");
	System.out.println("Contacts JWT Token ID:"+tokenId);
	ConfigManager.set("bearertoken", tokenId);
		
	}
	
	@Test
	public void getAllContactList() {
		
	Response response=	restClient.get(BASE_URL_CONTACTS, CONTACTS_ENDPOINTS, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	Assert.assertEquals(response.getStatusCode(), 200);	
	}

}
