package com.qa.api.mocking;

import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;;


public class APIMocks {

	public static void defineGetUserMock() {

		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")
						.withBody("{\r\n" + "    \"name\": \"seema\"\r\n" + "}")));

	}

	public static void defineGetUserMockwithJsonFile() {

		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")
						.withHeader("server-name", "Bank Server").withBodyFile("wireMockUser.json")));

	}

	public static void defineGetUserMockWithQueryParams() {

		stubFor(get(urlPathEqualTo("/api/users")).withQueryParam("name", equalTo("ravi")).willReturn(aResponse()
				.withStatus(200).withHeader("Content-Type", "application/json").withBodyFile("wireMockUser.json")));

	}
	
	
  //**************************Create mock.stub for Post Call***********************************************
	
	public static void defineCreateUser() {
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse()
				 .withStatus(201)
				 .withHeader("Content-Type", "application/json")
				  .withHeader("server-name", "bankserver")
				  .withBodyFile("wireMockUser.json")));
	}

}
