package com.sourcecode.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class updateUserDetailsBean {

    public HashMap<String, String> update(String fname,String lname,String username,String email,String password,String street,String city,
    		String state,String phone,String zip){

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
			Jsonstring = posttoClient.connect(header.updateserviceurl, urlParameters);
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
	
	
}
