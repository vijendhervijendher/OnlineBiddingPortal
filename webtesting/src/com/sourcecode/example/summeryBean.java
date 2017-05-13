package com.sourcecode.example;

import org.json.JSONObject;

public class summeryBean {

	public String getSummery(String username){
		   String Jsonstring;
		   JSONObject resultobj = new JSONObject(); 
		   try {
				Jsonstring = gettoClient.get(header.getsummeryurl+"/"+username);
				resultobj = new JSONObject(Jsonstring);
				
		   } catch (Exception e) {
				e.printStackTrace();
		   }
		   return resultobj.toString();
		
	}
	
	
}
