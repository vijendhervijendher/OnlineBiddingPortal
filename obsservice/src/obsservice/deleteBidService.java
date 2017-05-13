package obsservice;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

@Path(header.deletebidurl)
public class deleteBidService {

	@Path("/{productId}")
	@GET
	public String delete(@PathParam("productId") String itemid){
			
		
		JSONObject output = new JSONObject();
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		JSONObject queryobject = new JSONObject();
		queryobject.put(header.itemid_key,itemid);
			
		JSONObject updateobject = new JSONObject();
		updateobject.put(header.ended, header.Success);
		
		
		urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBproduct_details));
		urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
		urlParameters.add(new BasicNameValuePair(header.Updatestring_key, updateobject.toString()));
			
		 posttoClient.connect(header.Microserviceurl+"/"+header.writetodburl+"/"+header.updatepath_optionsurl, urlParameters);
//String Jsonstring =
//		JSONObject resultobj = new JSONObject(Jsonstring);
		
		output.put(header.Result_key, header.Success);
		return output.toString();
	}
	
}
