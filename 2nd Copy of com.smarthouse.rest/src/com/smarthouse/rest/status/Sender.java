package com.smarthouse.rest.status;

import Client.Client;

public class Sender {
	
	private String ip = "localhost";
	private int port = 4567;
	
	public String getResponse(String message){
		
		Client client = new Client(ip, port);
		client.start();
		client.setCommand(message);
		return client.getReply();
	}

}
