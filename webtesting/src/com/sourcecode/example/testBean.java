package com.sourcecode.example;

import org.json.JSONException;
import org.json.JSONObject;

public class testBean {

	private String aa= "";
	
	public testBean() throws JSONException{
		
		JSONObject jobj = new JSONObject();
		
		jobj.append("key1", "value1");
		jobj.append("key2", "value1");
		
		aa = jobj.toString();
	}
	
	public void post(){
		
		
		  
		
	}
}
