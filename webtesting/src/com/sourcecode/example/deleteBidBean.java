package com.sourcecode.example;

import org.json.JSONObject;

public class deleteBidBean {
	   public String delete(String itemid){
		   
		   String Jsonstring;
		   JSONObject resultobj = new JSONObject(); 
		   try {
				Jsonstring = gettoClient.get(header.deletebidserviceurl+"/"+itemid);
				resultobj = new JSONObject(Jsonstring);
				
		   } catch (Exception e) {
				e.printStackTrace();
		   }
		   return resultobj.toString(); 
	   }
}
