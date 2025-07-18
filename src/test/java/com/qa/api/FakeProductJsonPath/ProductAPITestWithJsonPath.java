package com.qa.api.FakeProductJsonPath;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.util.JsonPathValidatorUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithJsonPath extends BaseTest{
	
	@Test
	
	public void getProductTest() {
		
	Response response=	restClient.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINTS, null, null, AuthType.NO_AUTH, ContentType.ANY);
	
	List<String> ids= JsonPathValidatorUtil.readList(response, "$.[*].id");
	
      System.out.println(ids);
      
      List<Map<String,Object>>IdCatlist=JsonPathValidatorUtil.readListOfMaps(response,"$.[*].['id','category','image']");
		
		for(Map<String,Object> e:IdCatlist) {
			int id=(Integer)e.get("id");
			System.out.println(id);
			String cat=(String)e.get("category");
			System.out.println(cat);
			String image=(String)e.get("image");
			System.out.println(image);
		
		}
	}

}
