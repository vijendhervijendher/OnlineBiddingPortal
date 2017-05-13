package obsservice;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;



@Path(header.Uploadurl)
public class uploadservice {

	public static int pid = 13;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String upload(@FormParam(header.pname_key) String pname,@FormParam(header.Descriptio_key) String pdesc,
			             @FormParam(header.Username_key) String username,@FormParam(header.Base_key) String baseprice){
	
	 	JSONObject output = new JSONObject();
	 	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	 	String Jsonstring;
	    JSONObject resultobj = new JSONObject();
	    JSONObject insertobj = new JSONObject();
	    
	 	try {
			urlParameters.add(new BasicNameValuePair(header.Username_key, username.toLowerCase()));
			Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.chechuserurl, urlParameters);
			resultobj = new JSONObject(Jsonstring);
			if(resultobj.getString(header.Result_key).equals(header.Fail)){
				  MemcachedHandler mhandle = new MemcachedHandler();	
				  insertobj.put(header.Username_key, username);
				  insertobj.put(header.Descriptio_key, pdesc);
				  insertobj.put(header.pname_key, pname);
				  insertobj.put(header.Base_key, baseprice);
				  insertobj.put(header.DBtime, new Date().getTime());
				  insertobj.put(header.itemid_key, String.valueOf( pid));
				  insertobj.put(header.ended, header.Fail);
				  pid++;
				  
				  urlParameters.clear();
				  urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBproduct_details));
				  urlParameters.add(new BasicNameValuePair(header.Insertstring_key, insertobj.toString()));
				  
				  Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.writetodburl+"/"+header.newpathurl, urlParameters);
				  resultobj = new JSONObject(Jsonstring);
				  if(resultobj.getString(header.Result_key).equals(header.Success)){
					  	mhandle.tryInvalidate(header.memcached_catalogue_key);
						output.put(header.Result_key, header.Success);
						mhandle.Destroy();
				  }else{
					  output.put(header.Result_key, header.Fail);
					  output.put(header.error_key, "Internal error");
				  }
			}else{
				output.put(header.Result_key, header.Fail);
				output.put(header.error_key, "404 Action forbidden.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	
	 	return output.toString();

	}
}
