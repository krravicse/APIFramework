package com.qa.api.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.RestClient;
import com.qa.api.mocking.WireMockUpSetUp;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

//@Listeners(ChainTestListener.class)

public class BaseTest {
	protected RestClient restClient;
	
	//********************* API Base URLs*******************/
	protected final static String BASE_URL_GOREST="https://gorest.co.in";
	protected final static String BASE_URL_CONTACTS="https://thinking-tester-contact-list.herokuapp.com";
	protected final static String BASE_URL_REQRES="https://reqres.in";
	protected final static String BASE_URL_BASICAUTH="https://the-internet.herokuapp.com";
	protected final static String BASE_URL_PRODUCTS="https://fakestoreapi.com";
	protected final static String BASE_URL_OAUTH2_AMADEUS="https://test.api.amadeus.com";
	protected final static String BASE_URL_MOCKSERVER="http://localhost:8090";
	
	//************ API EndPoints*********************/
	protected final static String GOREST_USERS_ENDPOINTS="/public/v2/users";
	protected final static String CONTACTS_LOGIN_ENDPOINTS="/users/login";
	protected final static String CONTACTS_ENDPOINTS="/contacts";
	protected final static String REQRES_ENDPOINTS="/api/users";
	protected final static String BASICAUTH_ENDPOINTS="/basic_auth";
	protected final static String PRODUCTS_ENDPOINTS="/products";
	protected final static String AMADEUS_OAUTH2_TOKEN_ENDPOINTS="/v1/security/oauth2/token";
	protected final static String AMADEUS_FLIGHT_DEST_ENDPOINTS="/v1/shopping/{Destination}";
	protected final static String MOCKSERVER_ENDPOINTS="/api/users";
	
	@BeforeSuite
	public void initSetup() {
		RestAssured.filters(new AllureRestAssured());
	}
	@BeforeTest
	public void setup() {
		restClient=new RestClient();
		WireMockUpSetUp.createWireMockeServer();
	}
	@AfterTest
	
	public void setUp() {
		WireMockUpSetUp.stopWireMockServer();
	}
	
}
