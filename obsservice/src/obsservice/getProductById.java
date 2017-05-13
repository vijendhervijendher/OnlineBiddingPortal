package obsservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("product")
public class getProductById {
	
	private String jsonobject;
	private JSONObject jsonObject;

	@Path("{productId}")
	@GET
	
	public String getProduct(@PathParam("productId") String Id){
		
		String res = null;
		HttpClient client;
		client =  HttpClientBuilder.create().build();
		try {
			MemcachedHandler mhandle = new MemcachedHandler();
			
			HttpPost post = new HttpPost(header.Microserviceurl + "/" + header.Readurl + "/" + header.readonepathurl);
			List<NameValuePair> toSendParams = new ArrayList<>();
			JSONObject json = new JSONObject();
			json.put(header.itemid_key, Id);
			NameValuePair queryPair = new BasicNameValuePair(header.Querystring_key, json.toString());
			NameValuePair collectionPair = new BasicNameValuePair(header.Collection_key, header.DBproduct_details);
			toSendParams.add(collectionPair);
			toSendParams.add(queryPair);
			post.setEntity(new UrlEncodedFormEntity(toSendParams));
			if(mhandle.tryGet(json.toString()).equals("null")){
			
				HttpResponse response = client.execute(post);			
				res = EntityUtils.toString(response.getEntity());
				JSONParser parser = new JSONParser();
				jsonobject = (String) parser.parse(res);
				jsonObject = new JSONObject(jsonobject);
				mhandle.Put(json.toString(), res);
			}else{
				res = mhandle.tryGet(json.toString()).toString();
				JSONParser parser = new JSONParser();
				jsonobject = (String) parser.parse(res);
				jsonObject = new JSONObject(jsonobject);
			}	
			mhandle.Destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
}
