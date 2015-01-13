package com.smarthouse.rest.status;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import SmartFunctions.House;

public class JSONGenerator {
	
	private String[] statesArray;
	private String JSONString = "default JSON";
	private String serverResponse;
	
	public JSONGenerator(String serverResponse){
		this.statesArray = statesArray;
			
		
	}
	
	private String formatJSONSyntax(){
		House instance = new House();
		for(int i= 0; i<statesArray.length; i++){	
			
			ObjectMapper mapper = new ObjectMapper();// thing that makes json
			instance.getInside()[0].setName("light");
			try {
				JSONString = mapper.writeValueAsString(instance);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return JSONString;
	}

	
}
