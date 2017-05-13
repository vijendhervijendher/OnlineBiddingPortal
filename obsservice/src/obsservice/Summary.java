package obsservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;



@Path("profile")
public class Summary {
	
	private String jsonobject;
	private JSONObject jsonObject;
	private JSONArray JSONArray = new JSONArray();
		
	@Path("/{userId}")
	@GET
	public String getProfileInformation(@PathParam("userId") String userName){
		String user_details;
		String productDetails;
		String bids;
		JSONParser parser;
		HttpResponse response;
		HttpClient client;
		client =  HttpClientBuilder.create().build();
		JSONObject output = new JSONObject();
		String jsonString;
		
		HttpPost post = new HttpPost(header.Microserviceurl + "/" + header.Readurl + "/" + header.readonepathurl);
		List<NameValuePair> params1 = new ArrayList<>();
		
		JSONObject json = new JSONObject();
		json.put(header.Username_key, userName);
		NameValuePair queryPair = new BasicNameValuePair(header.Querystring_key, json.toString());
		NameValuePair collectionPair1 = new BasicNameValuePair(header.Collection_key, header.DBuser_details);
		params1.add(collectionPair1);
		params1.add(queryPair);
		jsonString = posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readonepathurl, params1);
		jsonObject = new JSONObject(jsonString);
		user_details = (String) jsonObject.get(header.Resultobj_key);
		
		
		output.put(header.DBuser_details, user_details);
		
		List<NameValuePair> params2 = new ArrayList<>();
		NameValuePair collectionPair2 = new BasicNameValuePair(header.Collection_key, header.DBproduct_details);
		params2.add(collectionPair2);
		params2.add(queryPair);
		jsonString = posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readmanypathurl, params2);
		jsonObject = new JSONObject(jsonString);
		productDetails =  (String) jsonObject.get(header.Resultobj_key);
		output.put(header.DBproduct_details, productDetails);
		
		List<NameValuePair> params3 = new ArrayList<>();
		NameValuePair collectionPair3 = new BasicNameValuePair(header.Collection_key, header.DBbids);
		params3.add(collectionPair3);
		params3.add(queryPair);
		jsonString = posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readmanypathurl, params3);
		jsonObject = new JSONObject(jsonString);
		bids =  (String) jsonObject.get(header.Resultobj_key);
		output.put(header.DBbids, bids);
	return output.toString();
}
}
