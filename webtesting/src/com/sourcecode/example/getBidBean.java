package com.sourcecode.example;

import org.json.JSONObject;

public class getBidBean {
	public String getbids(String itemid){
		   String Jsonstring;
		   JSONObject resultobj = new JSONObject(); 
		   try {
				Jsonstring = gettoClient.get(header.itembidsserviceurl+"/"+itemid);
				resultobj = new JSONObject(Jsonstring);
				
		   } catch (Exception e) {
				e.printStackTrace();
		   }
		   return resultobj.toString();
		
	}
	
}
