package obsservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

@Path("deletemyBid")
public class DeleteMyBidService {
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public String delete(@FormParam("itemid") String itemId,@FormParam("username") String username){
		
		JSONObject output = new JSONObject();
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		JSONObject queryobject = new JSONObject();
		queryobject.put(header.itemid_key,itemId);
		queryobject.put(header.Username_key, username);	
		JSONObject updateobject = new JSONObject();
		updateobject.put(header.deleted, header.Success);
		
		
		urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBbids));
		urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
		urlParameters.add(new BasicNameValuePair(header.Updatestring_key, updateobject.toString()));
			
		 posttoClient.connect(header.Microserviceurl+"/"+header.writetodburl+"/"+header.updatepath_optionsurl, urlParameters);
//String Jsonstring =
//		JSONObject resultobj = new JSONObject(Jsonstring);
		
		output.put(header.Result_key, header.Success);
		return output.toString();
	}
	
}
