package obsservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

@Path(header.updatepathurl)
public class UpdateProfileService {
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String upload(@FormParam(header.Username_key) String username, @FormParam(header.email_key) String email,
    		@FormParam(header.Fname_key) String fname,@FormParam(header.Lname_key) String lname,
    		@FormParam(header.Phone_key) String phone, @FormParam(header.Password_key) String password,
    		@FormParam(header.Street_key) String street, @FormParam(header.City_key) String city,
    		@FormParam(header.State_key) String state, @FormParam(header.Zip_key) String zip) throws JSONException{
							
		
		JSONObject output = new JSONObject();
	 	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	 	String Jsonstring;
	 	JSONObject queryObject = new JSONObject();
	    JSONObject resultobj = new JSONObject();
	    JSONObject updateobj = new JSONObject();
	    
	    queryObject.put(header.Username_key, username);
	    urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBuser_details));
	    urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryObject.toString()));
	    	if(! fname.isEmpty()){
	    		updateobj.put(header.Fname_key,fname.toLowerCase());
	    	}
	    	if(! lname.isEmpty()){
	    		updateobj.put(header.Lname_key,lname.toLowerCase());
	    	}
	    	if(! username.isEmpty()){
	    		updateobj.put(header.Username_key,username.toLowerCase());
		    }
	    	if(! email.isEmpty()){
	    		updateobj.put(header.email_key,email.toLowerCase());
		    }
	    	if(! password.isEmpty()){
			updateobj.put(header.Password_key,password);
	    	}
	    	if(! phone.isEmpty()){
			updateobj.put(header.Phone_key,phone);
	    	}
	    	if(! street.isEmpty()){
			updateobj.put(header.Street_key,street.toLowerCase());
	    	}
			if(! city.isEmpty()){
	    	updateobj.put(header.City_key,city.toLowerCase());
			}
			if(! state.isEmpty()){
	    	updateobj.put(header.State_key,state.toLowerCase());
			}
			if(! zip.isEmpty()){
	    	updateobj.put(header.Zip_key,zip);
			}
			urlParameters.add(new BasicNameValuePair(header.Updatestring_key, updateobj.toString()));
			Jsonstring = posttoClient.connect(header.Microserviceurl + "/" + header.writetodburl + "/" + header.updatepath_optionsurl, urlParameters);
			resultobj = new JSONObject(Jsonstring);
			if(resultobj.getString(header.Result_key).equals(header.Success)){
				output.put(header.Result_key, header.Success);
			}else{
				output.put(header.Result_key, header.Fail);
				output.put(header.error_key, "Internal error");
			}
	    
	 	
	 	return output.toString();
	}
	}

