package com.sourcecode.example;

import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class posttoClient {

	public static String connect(String url,List<NameValuePair> urlParameters){
		
        HttpClient client;
        HttpResponse res;
        JSONObject output = new JSONObject();
        JSONParser parser = new JSONParser(); 
        client =  HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			res = client.execute(post);
			String json = EntityUtils.toString(res.getEntity());
			String outputString =(String) parser.parse(json); 
			output = new JSONObject(outputString);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
