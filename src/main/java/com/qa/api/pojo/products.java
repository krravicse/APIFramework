package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class products {
	
	private Integer id;
	private String title;
	private Double price;
	private String description;
	private String category;
	private String image;
	private rating rating;
	
	
	@Builder
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class rating{
		private  Double rate;
		private Integer count;
	}
	

}
