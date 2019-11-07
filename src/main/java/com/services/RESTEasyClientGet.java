package com.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONObject;

public class RESTEasyClientGet {	

	public JSONObject getWeather(String city) {
		
		String output = new String();
		JSONObject replay = new JSONObject();
		
		try {

			ClientRequest request = new ClientRequest(
					"https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=a65955973ba33fce0e4fd6ad13a49543");
			request.accept("application/json");
			ClientResponse<String> response = request.get(String.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(response.getEntity().getBytes())));

			System.out.println("Output from Server .... \n");
			
			output = br.readLine();
			
			JSONObject json = new JSONObject(output);
			
			replay = json.getJSONObject("main");
			

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		return replay;
	}

}