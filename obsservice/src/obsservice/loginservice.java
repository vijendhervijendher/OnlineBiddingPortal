package obsservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

@Path(header.Loginurl)
public class loginservice {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam(header.Username_key) String username, @FormParam(header.Password_key) String password){
     
		JSONObject output = new JSONObject();
		JSONObject queryobject;
		JSONObject updateobject;
		JSONObject resultobj = new JSONObject();
		String Jsonstring;
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		
		queryobject = new JSONObject();
		queryobject.put(header.Username_key,username.toLowerCase());
		queryobject.put(header.Password_key,password);
		urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBuser_details));
		urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
		
		try {
			MemcachedHandler mHandler = new MemcachedHandler();
			
			if(mHandler.tryGet(queryobject.toString()).equalsIgnoreCase("null")){
				Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.Readurl+"/"+header.countpathurl, urlParameters);
				mHandler.Put(queryobject.toString(), Jsonstring);
				resultobj = new JSONObject(Jsonstring);
			}else{
				Jsonstring = mHandler.tryGet(queryobject.toString());
			}
			mHandler.Destroy();
		if(resultobj.getString(header.Result_key).equals(header.Success) && resultobj.getInt(header.count_key) == 1){
			output.put(header.Result_key, header.Success);
			
			//update the last log in time
			urlParameters.clear();
			queryobject = new JSONObject();
			queryobject.put(header.Username_key,username.toLowerCase());
			
			updateobject = new JSONObject();
			updateobject.put(header.lastlogin_key, new Date());
			urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBuser_details));
			urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
			urlParameters.add(new BasicNameValuePair(header.Updatestring_key, updateobject.toString()));
			
			Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.writetodburl+"/"+header.updatepath_optionsurl, urlParameters);
			resultobj = new JSONObject(Jsonstring);
		}else{
			
			output.put(header.Result_key, header.Fail);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	} 		
}