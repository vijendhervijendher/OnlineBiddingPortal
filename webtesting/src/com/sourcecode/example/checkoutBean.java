package com.sourcecode.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class checkoutBean {
	public checkoutBean(){
		
	}
	public HashMap<String, String> checkout(String itemid,String yourbid,String username){
    	String Jsonstring;
    	JSONObject resultobj;
    	HashMap<String, String> output = new HashMap<String, String>();
    	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    	
    	urlParameters.add(new BasicNameValuePair(header.Username_key, username));
		urlParameters.add(new BasicNameValuePair(header.itemid_key, itemid));
		urlParameters.add(new BasicNameValuePair(header.bidvalue_key, yourbid));

		try {
			Jsonstring = posttoClient.connect(header.checkoutserviceserviceurl, urlParameters);
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
