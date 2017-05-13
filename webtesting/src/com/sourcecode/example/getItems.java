package com.sourcecode.example;

import org.json.JSONObject;



public class getItems {
   public String items(){
	   
	   String Jsonstring;
	   JSONObject resultobj = new JSONObject(); 
	   try {
			Jsonstring = gettoClient.get(header.catalogueserviceurl);
			resultobj = new JSONObject(Jsonstring);
			
	   } catch (Exception e) {
			e.printStackTrace();
	   }
	   return resultobj.toString(); 
   }
}
