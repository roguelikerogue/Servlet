package com.smarthouse.rest.status;

import Client.Client;

public class Sender {
	
	private String ip = "localhost";
	private int port = 7239;//local port number 7239
	//private int port = 5906; //remote port number
	public String getResponse(String message){
		
		Client client = new Client(ip, port);
		client.start();
		client.setCommand(message);
		return client.getReply();
	}

}
