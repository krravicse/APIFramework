package com.qa.api.util;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorUtil {
	
	
	private static ReadContext getJsonResponseAsString(Response response) {
		String responseString=response.getBody().asString();
		 ReadContext ctx=  JsonPath.parse(responseString); 
		 return ctx;
	}
	
	public static<T> T read(Response response,String jsonPath) {
		return getJsonResponseAsString(response).read(jsonPath);
	}

	
	public static<T> List<T> readList(Response response,String jsonPath) {
		return getJsonResponseAsString(response).read(jsonPath);
	}

	
	public static<T> List<Map<String,T>> readListOfMaps(Response response,String jsonPath) {
		return getJsonResponseAsString(response).read(jsonPath);
	}

}
