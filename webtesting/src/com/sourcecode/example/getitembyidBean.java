package com.sourcecode.example;

import org.json.JSONObject;

public class getitembyidBean {
   
	public getitembyidBean(){
		
	}
	public String getitem(String itemid){
		   String Jsonstring;
		   JSONObject resultobj = new JSONObject(); 
		   try {
				Jsonstring = gettoClient.get(header.getproductserviceurl+"/"+itemid);
				resultobj = new JSONObject(Jsonstring);
				
		   } catch (Exception e) {
				e.printStackTrace();
		   }
		   return resultobj.toString(); 
	}
	
}
