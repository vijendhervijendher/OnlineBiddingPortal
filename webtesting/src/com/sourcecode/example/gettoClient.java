package com.sourcecode.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class gettoClient {

	
	public static String get(String url){
        HttpClient client;
        HttpResponse res;
        JSONObject output = new JSONObject();
        JSONParser parser = new JSONParser(); 
        client =  HttpClientBuilder.create().build();
		HttpGet getrequest = new HttpGet(url);
		try {
			res = client.execute(getrequest);
			String json = EntityUtils.toString(res.getEntity());
			String outputstring = (String) parser.parse(json);
			output = new JSONObject(outputstring);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();

	}
	
}
