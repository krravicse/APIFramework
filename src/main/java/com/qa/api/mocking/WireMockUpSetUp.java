package com.qa.api.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WireMockUpSetUp {
	private static WireMockServer server;
	
	public static void createWireMockeServer() {
		server=new WireMockServer(8090);
		WireMock.configureFor("localhost",8090);
		server.start();
		
	}
	public static void stopWireMockServer() {
		server.stop();
	}

}
