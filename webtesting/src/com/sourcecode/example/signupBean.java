package com.sourcecode.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
public class signupBean {

	private String fname;
	private String lname;
	private String email;
	private String username;
	private String password;
	private String street;
	private String city;
	private String state;
	private String phone;
	private String zip;

    public HashMap<String, String> signup(){

    	String Jsonstring;
    	JSONObject resultobj;
    	HashMap<String, String> output = new HashMap<String, String>();
    	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    	
    	urlParameters.add(new BasicNameValuePair(header.Fname_key, fname));
		urlParameters.add(new BasicNameValuePair(header.Lname_key, lname));
		urlParameters.add(new BasicNameValuePair(header.Username_key, username));
		urlParameters.add(new BasicNameValuePair(header.email_key, email));
		urlParameters.add(new BasicNameValuePair(header.Password_key, password));
		urlParameters.add(new BasicNameValuePair(header.Street_key, street));
		urlParameters.add(new BasicNameValuePair(header.City_key, city));
		urlParameters.add(new BasicNameValuePair(header.State_key, state));
		urlParameters.add(new BasicNameValuePair(header.Phone_key, phone));
		urlParameters.add(new BasicNameValuePair(header.Zip_key, zip));
		
		try {
			Jsonstring = posttoClient.connect(header.signupserviceurl, urlParameters);
			resultobj = new JSONObject(Jsonstring);
			if(resultobj.getString(header.Result_key).equals(header.Success)){
				output.put(header.Result_key, header.Success);
			}else{
				output.put(header.Result_key, header.Fail);
				output.put(header.error_key, resultobj.getString(header.error_key));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
    }

	
	
	
	
	
	
	

	public String getfname(String value){
		return fname;
	}
	public String getlname(String value){
		return lname;
	}
	public String getemail(String value){
		return email;
	}
	public String getusername(String value){
		return username;
	}
	public String getpassword(String value){
		return password;
	}
	public String getstreet(String value){
		return street;
	}
	public String getcity(String value){
		return city;
	}
	public String getstate(String value){
		return state;
	}
	public String getzip(String value){
		return zip;
	}
	public String getphone(String value){
		return phone;
	}
    
	public void setfname(String value){
		fname = value;
	}
	public void setlname(String value){
		lname = value;
	}
	public void setemail(String value){
		email = value;
	}
	public void setusername(String value){
		username = value;
	}
	public void setpassword(String value){
		password = value;
	}
	public void setstreet(String value){
		street = value;
	}
	public void setcity(String value){
		city = value;
	}
	public void setstate(String value){
		state = value;
	}
	public void setzip(String value){
		zip = value;
	}
	public void setphone(String value){
		phone = value;
	}
}
