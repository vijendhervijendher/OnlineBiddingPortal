package obsservice;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

//Entry point to signing up for the web site.
//Accepts only POST method any other method will throw an error.
//
//Input should be in forms.
//when the operation is successful, it sends JSON object with result 1
//if not successful , result is 0 and with error msg. 

@Path(header.Signupurl)
public class signupservice {
		
	    @POST
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.APPLICATION_JSON)
	    public String Signmeup(
	    		@FormParam(header.Username_key) String username, @FormParam(header.email_key) String email,
	    		@FormParam(header.Fname_key) String fname,@FormParam(header.Lname_key) String lname,
	    		@FormParam(header.Phone_key) String phone, @FormParam(header.Password_key) String password,
	    		@FormParam(header.Street_key) String street, @FormParam(header.City_key) String city,
	    		@FormParam(header.State_key) String state, @FormParam(header.Zip_key) String zip) throws JSONException {


	    	//check for duplicate username
	    	int count = 0;
	    	String Jsonstring;
	    	String error = "";
	    	JSONObject resultobj;
	    	JSONObject insertobj = new JSONObject();
	    	JSONObject queryobject = new JSONObject();
	    	JSONObject output = new JSONObject();
	    	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	    	
	    	
	    	try {
	    		urlParameters.add(new BasicNameValuePair(header.Username_key, username.toLowerCase()));
				Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.chechuserurl, urlParameters);
				resultobj = new JSONObject(Jsonstring);
				if(resultobj.getString(header.Result_key).equals(header.Success)){
					count = 1;
				}else{
					count = 0;
					error += "Username already in use.";
				}
				
				urlParameters.clear();
				queryobject.put(header.email_key,email.toLowerCase());
				urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBuser_details));
				urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
				Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.Readurl+"/"+header.countpathurl, urlParameters);
				resultobj = new JSONObject(Jsonstring);
				if(resultobj.getString(header.Result_key).equals(header.Success) && resultobj.getInt(header.count_key) == 0){
					count = 1;
				}else{
					count = 0;
					error += "Email already in use.";
				}
				//header.Collection_key, header.DBuser_details
				if(count == 1){
					insertobj.put(header.Fname_key,fname.toLowerCase());
					insertobj.put(header.Lname_key,lname.toLowerCase());
					insertobj.put(header.Username_key,username.toLowerCase());
					insertobj.put(header.email_key,email.toLowerCase());
					insertobj.put(header.Password_key,password);
					insertobj.put(header.Phone_key,phone);
					insertobj.put(header.Street_key,street.toLowerCase());
					insertobj.put(header.City_key,city.toLowerCase());
					insertobj.put(header.State_key,state.toLowerCase());
					insertobj.put(header.Zip_key,zip);
					
					urlParameters.clear();
					urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBuser_details));
					urlParameters.add(new BasicNameValuePair(header.Insertstring_key, insertobj.toString()));
					
					Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.writetodburl+"/"+header.newpathurl, urlParameters);
					resultobj = new JSONObject(Jsonstring);
					if(resultobj.getString(header.Result_key).equals(header.Success)){
						output.put(header.Result_key, header.Success);
					}else{
						output.put(header.Result_key, header.Fail);
						output.put(header.error_key, "Internal error");
					}
				}else{
					output.put(header.Result_key, header.Fail);
					output.put(header.error_key, error);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	            return output.toString();
        }
}