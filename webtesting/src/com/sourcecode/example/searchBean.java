package com.sourcecode.example;

import org.json.JSONObject;

public class searchBean {
	   public String searchitems(String query){
		   
		   String Jsonstring;
		   JSONObject resultobj = new JSONObject(); 
		   try {
				Jsonstring = gettoClient.get(header.searchserviceurl+"/"+query);
				resultobj = new JSONObject(Jsonstring);
				
		   } catch (Exception e) {
				e.printStackTrace();
		   }
		   return resultobj.toString(); 
	   }
}
