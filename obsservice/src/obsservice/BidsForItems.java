package obsservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

@Path("bids")
public class BidsForItems {
	
	@Path("{productId}")
	@GET
	public String getBids(@PathParam("productId") String Id){
		
	List<NameValuePair> params1 = new ArrayList<>();	
	JSONObject json = new JSONObject();
	String jsonString;
	JSONObject jsonObject;
	JSONObject output = new JSONObject();
	String user_details;

	json.put(header.itemid_key, Id);
	NameValuePair queryPair = new BasicNameValuePair(header.Querystring_key, json.toString());
	NameValuePair collectionPair1 = new BasicNameValuePair(header.Collection_key, header.DBbids);
	params1.add(collectionPair1);
	params1.add(queryPair);
	jsonString = posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readmanypathurl, params1);
	jsonObject = new JSONObject(jsonString);
	user_details = (String) jsonObject.get(header.Resultobj_key);
	
	jsonObject.put(header.DBbids, user_details);
	
	if(jsonObject.getString(header.Result_key).equals(header.Success)){
		output.put(header.Resultobj_key, jsonObject.get(header.Resultobj_key));
		output.put(header.Result_key, header.Success);
	}else{
		
		output.put(header.Result_key, header.Fail);
	}
	return output.toString();
}
}
