package obsservice;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

@Path("search")
public class SearchService {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{searchString}")
	public String Search(@PathParam("searchString") String searchString){
		JSONObject output = new JSONObject();
		JSONObject queryobject = new JSONObject();
		String Jsonstring;
		JSONObject resultobj;

		queryobject.put(header.pname_key,searchString);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBproduct_details));
		urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
		try {
			MemcachedHandler mHandler = new MemcachedHandler();
			if(mHandler.tryGet(queryobject.toString()).equalsIgnoreCase("null")){
				Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.Readurl+"/"+header.readmanypathurl, urlParameters);
				mHandler.Put(queryobject.toString(), Jsonstring);
			}else{
				Jsonstring = mHandler.tryGet(queryobject.toString());
			}
			mHandler.Destroy();
			resultobj = new JSONObject(Jsonstring);
			if(resultobj.getString(header.Result_key).equals(header.Success)){
				output.put(header.Resultobj_key, resultobj.get(header.Resultobj_key));
				output.put(header.Result_key, header.Success);
			}else{
				
				output.put(header.Result_key, header.Fail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			output.put(header.Result_key, header.Fail);
		}
		
		return output.toString();
	}
	
}
