package com.qa.api.client;

import java.util.Base64;
import java.util.Map;

import com.qa.api.apiExceptions.APIException;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import static io.restassured.RestAssured.expect;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class RestClient {
	
	//define Response Specs
	 private ResponseSpecification responseSpec200=  expect().statusCode(200);
	 private ResponseSpecification responseSpec201=  expect().statusCode(201);
	 private ResponseSpecification responseSpec204=  expect().statusCode(204);
	 private ResponseSpecification responseSpec400=  expect().statusCode(400);
	 private ResponseSpecification responseSpec200or201=  expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	 private ResponseSpecification responseSpec200or404=  expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	
	private RequestSpecification setUpRequest(String baseUrl,AuthType authtype,ContentType contentType) {
		
		RequestSpecification request=  RestAssured.given().log().all()
		.baseUri(baseUrl)
		.contentType(contentType)
		.accept(contentType);
		
		switch (authtype) {
		case BEARER_TOKEN: {
			request.header("Authorization","Bearer "+ConfigManager.get("bearertoken"));
			break;
		}
		case OAUTH2: {
			request.header("Authorization","Bearer "+"OAuth2 ");
			break;
		}
		case BASIC_AUTH: {
			request.header("Authorization","Basic "+generateBasicAuthToken());
			break;
		}case API_KEY: {
			request.header("x-api-key","Bearer "+"");
			break;
		}case NO_AUTH: {
			System.out.println("Auth Not required");
			break;
		}
		default:
			System.out.println("This auth not supported---");
			throw new APIException("Invalid Auth");
		}
		return request;
	}
	
	private String generateBasicAuthToken() {
	String credentials=	ConfigManager.get("basicUserName")+":"+ConfigManager.get("basicPassword");
	return Base64.getEncoder().encodeToString(credentials.getBytes());
	}
	
	private void applyparams(RequestSpecification request,Map<String,String> queryParams,Map<String,String> pathParams) {
		if(queryParams!=null) {
			request.queryParams(queryParams);
		}
		if(pathParams!=null) {
			request.pathParams(pathParams);
		}
	}
	
	
	//CRUD:
	
	//get:
	
	/**
	 * This method is used to call GET API
	 * @param baseUrl
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return it returns GET api call response
	 */
	
	public Response get(String baseUrl,String endPoint,
			Map<String,String> queryParams,
			Map<String,String> pathParams,
			AuthType authType,
			ContentType contentType) {
		
		 RequestSpecification request=   setUpRequest(baseUrl, authType, contentType);
		 applyparams(request, queryParams, pathParams);
		 Response response= request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		 response.prettyPrint();
		 return response;
	}
	
	
	//post
	public <T>Response post(String baseUrl,String endPoint,T body,
			Map<String,String> queryParams,
			Map<String,String> pathParams,
			AuthType authType,
			ContentType contentType) {
		RequestSpecification request=   setUpRequest(baseUrl, authType, contentType);
		 applyparams(request, queryParams, pathParams);
		 Response response= request.body(body).post(endPoint).then().spec(responseSpec200or201).extract().response();
	      response.prettyPrint();
	      return response;	
	}
	
	//put
		public <T>Response put(String baseUrl,String endPoint,T body,
				Map<String,String> queryParams,
				Map<String,String> pathParams,
				AuthType authType,
				ContentType contentType) {
			RequestSpecification request=   setUpRequest(baseUrl, authType, contentType);
			 applyparams(request, queryParams, pathParams);
			 Response response= request.body(body).put(endPoint).then().spec(responseSpec200).extract().response();
		      response.prettyPrint();
		      return response;	
		}
		
	//patch
		public <T>Response patch(String baseUrl,String endPoint,T body,
				Map<String,String> queryParams,
				Map<String,String> pathParams,
				AuthType authType,
				ContentType contentType) {
			RequestSpecification request=   setUpRequest(baseUrl, authType, contentType);
			 applyparams(request, queryParams, pathParams);
			 Response response= request.body(body).patch(endPoint).then().spec(responseSpec201).extract().response();
		      response.prettyPrint();
		      return response;	
		}
		
		//delete
				public Response delete(String baseUrl,String endPoint,
						Map<String,String> queryParams,
						Map<String,String> pathParams,
						AuthType authType,
						ContentType contentType) {
					RequestSpecification request=   setUpRequest(baseUrl, authType, contentType);
					 applyparams(request, queryParams, pathParams);
					 Response response= request.delete(endPoint).then().spec(responseSpec204).extract().response();
				      response.prettyPrint();
				      return response;	
				}
	
				//post
				public Response post(String baseUrl,String endPoint,String clientId,
						String clientSecret,String grantType,ContentType contentType) {
					RestAssured.useRelaxedHTTPSValidation();
					Response response=RestAssured.given().log().all()
					.contentType(ContentType.URLENC)
					.formParam("grant_type", grantType)
					.formParam("client_id", clientId)
					.formParam("client_secret", clientSecret)
					.when()
					.post(baseUrl+endPoint);
					response.prettyPrint();
					return response;
				}
				

}
