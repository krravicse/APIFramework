package com.qa.api.products.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.products;
import com.qa.api.util.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest {
	
	@Test
	
	public void getProductTest() {
		
	Response response=restClient.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINTS, null, null, AuthType.NO_AUTH, ContentType.ANY);
	 products[] product=  JsonUtils.deserialize(response, products[].class);
	 
	 for(products p:product) {
			System.out.println("id: "+p.getId());
			System.out.println("title: "+p.getTitle());
			System.out.println("price: "+p.getPrice());
			System.out.println("description: "+p.getDescription());
			System.out.println("category: "+p.getCategory());
			System.out.println("image: "+p.getImage());
			
			
			System.out.println("rate: "+p.getRating().getRate());
			System.out.println("count: "+p.getRating().getCount());
			
		}
	}
	

}
