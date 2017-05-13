package com.sourcecode.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class loginBean {
     public loginBean(){
    	 //connect to database
     }
     public boolean isValiduser(String username,String password){

    	String Jsonstring;
     	JSONObject resultobj;
     	HashMap<String, String> output = new HashMap<String, String>();
     	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

 		urlParameters.add(new BasicNameValuePair(header.Username_key, username));
 		urlParameters.add(new BasicNameValuePair(header.Password_key, password));
 		 		
 		try {
 			Jsonstring = posttoClient.connect(header.loginserviceurl, urlParameters);
 			resultobj = new JSONObject(Jsonstring);
 			if(resultobj.getString(header.Result_key).equals(header.Success))
 				return true;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}    	 
    	 return false;
     }
}
