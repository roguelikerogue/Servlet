package com.smarthouse.rest.status;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONString;

import SmartFunctions.House;
import Client.Client;

//TO DO
//read shit from server and change to json / pass replies

/*Info on SSL
 * If I understand your problem correctly, you are publishing a URL for http from a web page served by your servlet.
 If you need to change the request to be https instead you should redirect your plain http connector (in port 80 or 8080 where you have it) to connector for port 443.
 If you google tomcat redirect http to https you wil find plenty of links e.g. redirect tomcat to https
 * */

@Path("/services")
public class Resources {

	// THIS IS AN EDIT 2
	@GET
	@Path("protoTest")
	@Produces(MediaType.TEXT_HTML)
	public String protoTest() {
		String temp = "test";
		Client client = new Client("localhost", 4567);
		client.start();
		client.setCommand("test_Dinosaurs will get you while you sleep");
		String string = client.getReply();
		return "<p>Your message: " + string + "</p>";
	}

	@GET
	@Path("IPTest")
	@Produces(MediaType.TEXT_HTML)
	// Error when I use @produces(MediaType.TEXT_HTML) NO IDEA WHY. DID NOT DO
	// BEFORE.
	public String activate(@Context HttpServletRequest requestContext,
			@Context SecurityContext context) {
		String yourIP = requestContext.getRemoteAddr().toString();
		String editedIP = yourIP.replaceAll("0", "9");
		System.out.println("Connection test from IP: " + yourIP);
		return editedIP;
	}

	@POST
	@Path("POSTTest")
	@Produces(MediaType.TEXT_HTML)
	public String postTest(@Context HttpServletRequest request) {
		String state = request.getParameter("state");
		return "<p>Post request data " + state + "<p>";
	}

	// Write like this
	// http://localhost:8080/com.smarthouse.rest/api/services/iliketuna@fishlovers.com/houses
	@GET
	@Path("{email}/houses")
	@Produces(MediaType.TEXT_HTML)
	public Response getHouses(
			@DefaultValue("default") @PathParam("email") String email) {

		String output = " This is your email: " + email;
		System.out.println("Client requesting accessible houses");

		// Just testing another way to return a response
		return Response.status(200).entity(output).build();
	}

	// Write like this
	// OLD
	// http://localhost:8080/com.smarthouse.rest/api/services/iliketuna@fishlovers.com/house/7
	// NEW //
	// http://services/{email}/house/{houseId}/device/{id}?state=something
	//Add state to this because it's missing
	@GET
	@Path("{email}/house/{houseId}/device/{deviceId}")
	@Produces(MediaType.TEXT_HTML)
	public Response toggleDevice(
			@DefaultValue("default email") @PathParam("email") String email,
			@DefaultValue("default house ID") @PathParam("houseId") String houseId,
			@DefaultValue("default device id") @PathParam("deviceId") String deviceId,
			@DefaultValue("default state") @QueryParam("state") String state) {

		// String output = " This is your email: " + email +
		// " This is your id: "+houseId+" This is your device id: "+deviceId+" This is your state: "+state;
		// System.out.println("Client requesting house "+houseId+" device information");

		String output = "<p>toggleDevice_ " + deviceId + "_" + state + "_</p>";
		// Just testing another way to return a response
		return Response.status(200).entity(output).build();

	}

	// Type like this
	// http://localhost:8080/com.smarthouse.rest/api/services/login?SSN=cameron&password=tuna
	@POST
	@Path("login")
	@Produces(MediaType.TEXT_HTML)
	// can also do TEXT_PLAIN
	public String login(
			@DefaultValue("default SSN") @QueryParam("SSN") String SSN,
			@DefaultValue("default password") @QueryParam("password") String password) {
		System.out.println("SSN = " + SSN + " Password: " + password);
		Sender sender = new Sender();
		// REMOVE "TEST" from String
		//String message = "authenticate_" + SSN + "_" + password + "_";
		String message ="unauthorized_12343234_Cameron_brownlee_cameron@gmail.com_ilikecats_1";
		
		//example confirmations
		String confirmation = "authenticate_12 fältvägen_29139_kristianstad_sweden_1_cameron_brownlee_true";
		//String confirmation = "webAuthenticate_adress_zip_city_country_houseId_firsName_lastName_sureName_idAdmin_";
		//String confirmation = sender.getResponse(message);
		String response = "error";
		if(confirmation.contains("authenticate_")){
			
			response = confirmation; // same string minus"authenticate_ part
			//response = confirmation.substring(16, confirmation.length()); // same string minus"authenticate_ part
			int indexArrayLength = 10;
			int[] indexArray= new int[indexArrayLength];
			//Print out all instances 
			int indexCount = 0;
			//This method counts the occurrence of "_" puts their indexes in an array
			for (int i = -1; (i = response.indexOf("_", i + 1)) != -1; ) {
				indexArray[indexCount] = i;
			    System.out.println("Value of index "+indexArray+": "+ indexArray[indexCount]);
				indexCount++;
			}
			for(int i = 0; i<indexArrayLength; i++){
				System.out.println("Test: "+ indexArray[i]);
			}
			
			//ELEMENT 4 HAS THE INDEX 1 BEFORE THE HOUSE ID. There are some extra parameters we might use
			String houseAddress = response.substring(indexArray[0]+1, indexArray[4]);
			houseAddress = houseAddress.replaceAll("_", " ");
			String houseID = response.substring(indexArray[4]+1, indexArray[5]);
			//int houseID = String.parseInt(response.substring(indexArray[3]+1, indexArray[4]+1));
			System.out.println(houseID);
			System.out.println(houseAddress);
			
			
			//response = response.replaceAll("_", " ");
			//response = sender.getResponse(message);
		}
		//return response;
		//return confirmation;
		return sender.getResponse(message);

	}
	
	@POST
	@Path("loginTest")
	@Produces(MediaType.TEXT_HTML)
	// can also do TEXT_PLAIN
	public String loginTest() {
		Sender sender = new Sender();
		String message = "authenticate_12 fältvägen_29139_kristianstad_sweden_1_cameron_brownlee_true";
		
		return sender.getResponse(message);

	}


	@POST
	@Path("test")
	@Produces(MediaType.TEXT_HTML)
	// Write like this
	// http://localhost:8080/com.smarthouse.rest/api/services/test?name=light&state=true&selected=3
	public String TestJson(@QueryParam("name") String name,
			@QueryParam("state") boolean state,
			@QueryParam("selected") int selected) {
		return "<p>Name  " + name + "</p><p>  state " + state
				+ "</p><p>selected " + selected + "</p>";

	}

	// http://services/{email}/house/{id}/device/{id}?state=something
	// URL like this:
	// http://localhost:8080/com.smarthouse.rest/api/services/toggle?device=light&state=true
	@GET
	@Path("toggle")
	@Produces(MediaType.TEXT_HTML)
	public String TestJSONToggle(@QueryParam("device") String device,
			@QueryParam("state") String state) {

		House instance = new House();
		String jsonString = null;
		ObjectMapper mapper = new ObjectMapper();// thing that makes json
		instance.getInside()[0].setName("light");
		try {
			jsonString = mapper.writeValueAsString(instance);
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

		int id = 0;
		// if(device.equals("fireAlarm"))
		// id = 1;
		// else if(device.equals("stove"))
		// id = 2;
		// else if(device.equals("waterLeakage"))
		// id = 3;
		// else if(device.equals("windowOpen"))
		// id = 4;
		// else if(device.equals("doorOpen"))
		// id = 5;
		// else if(device.equals("ElectricityCut"))
		// id = 6;
		// else if(device.equals("lightInside"))
		// id = 7;
		// else if(device.equals("AutoLightInside"))
		// id = 8;
		// else if(device.equals("lightOutside"))
		// id = 9;
		// else if(device.equals("autoLightOutside"))
		// id = 10;
		// else if(device.equals("heaterInside"))
		// id = 11;
		// else if(device.equals("heaterRoof"))
		// id = 12;
		// else if(device.equals("fan"))
		// id = 13;
		// else if(device.equals("autoAirConditioning"))
		// id = 14;
		// else if(device.equals("temperatureOutside"))
		// id = 15;
		// else if(device.equals("temperatureInside"))
		// id = 16;
		// else if(device.equals("temperatureInsideRoof"))
		// id = 17;
		// else if(device.equals("electricityConsumption"))
		// id = 18;
		// else if(device.equals("securityAlarm"))
		// id = 19;

		return jsonString;
		// return "<p>toggleDevice_ " +id + "_"+state+"_</p>";

		// instance.setAddress("321 Real St, Realville");
		// instance.setAddress("321 Real St, Realville");
		// instance.setAddress("321 Real St, Realville");
		// jsonString = "Cat";

	}

}