package obsservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

@Path(header.catalogueurl)
public class productcatalogue {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String testservice(){
		
		JSONObject output = new JSONObject();
		JSONObject queryobject = new JSONObject();
		queryobject.put(header.ended, header.Fail);
		String Jsonstring;
		JSONObject resultobj = new JSONObject();
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBproduct_details));
		urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
		
		try {
			MemcachedHandler mhandle = new MemcachedHandler();
			if(mhandle.tryGet(header.memcached_catalogue_key).equals("null")){
				Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.Readurl+"/"+header.readmanypathurl, urlParameters);
				resultobj = new JSONObject(Jsonstring);
				mhandle.Put(header.memcached_catalogue_key,Jsonstring);
			}else{
				System.out.println("im here");
				Jsonstring = mhandle.tryGet(header.memcached_catalogue_key);
				resultobj = new JSONObject(Jsonstring);
			}
			mhandle.Destroy();
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
