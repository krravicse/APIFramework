package com.qa.api.amadeus.tests;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmadeusAPItest extends BaseTest{
	private String accessToken;
	@BeforeMethod
	
	public void getOAuth2Token() {
		
	Response response=	restClient.post(BASE_URL_OAUTH2_AMADEUS, AMADEUS_OAUTH2_TOKEN_ENDPOINTS, ConfigManager.get("client_id"),
				ConfigManager.get("client_secret"), ConfigManager.get("grant_type"), ContentType.URLENC);
	
	         accessToken=response.jsonPath().getString("access_token");
	         System.out.println("AccessToken is:"+accessToken);
	         ConfigManager.set("bearertoken", accessToken);
	}
	
	@Test
	
	public void getFlightDetails() {
		Map<String, String> queryParams=Map.of("origin","PAR","maxPrice","200");
		Map<String, String> pathParams=Map.of("Destination","flight-destinations");
		 Response response=restClient.get(BASE_URL_OAUTH2_AMADEUS, AMADEUS_FLIGHT_DEST_ENDPOINTS, queryParams, pathParams, AuthType.BEARER_TOKEN, ContentType.ANY);
		 System.out.println(response.getStatusCode());
	}

}
