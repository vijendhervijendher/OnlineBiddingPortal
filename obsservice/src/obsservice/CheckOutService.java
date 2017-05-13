package obsservice;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


@Path(header.Checkouturl)
public class CheckOutService {

	  @POST
      @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
      @Produces(MediaType.APPLICATION_JSON)
      public String checkout(@FormParam(header.itemid_key) String itemid,@FormParam(header.Username_key) String username
    		  ,@FormParam(header.bidvalue_key) String value){
    	  
		  int count = 1;
    	  JSONObject output = new JSONObject(); 
    	  JSONObject resultobj;
    	  JSONObject queryobject = new JSONObject();
    	  JSONObject insertobj = new JSONObject();
    	  String Jsonstring;
    	  String error = "";
  		  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

    	  try {
    		  
    		  	urlParameters.add(new BasicNameValuePair(header.Username_key, username.toLowerCase()));
    		  	Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.chechuserurl, urlParameters);
    		  	resultobj = new JSONObject(Jsonstring);
    		  	if(resultobj.getString(header.Result_key).equals(header.Success)){
    		  		count = 0;
    		  		error = "404 Action forbiden. 1";
    		  	}
    		  	
    		  	urlParameters.clear();
    		  	queryobject.put(header.itemid_key,itemid);
    		  	queryobject.put(header.ended,header.Fail);
    		  	urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBproduct_details));
    		  	urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
    		  	Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.Readurl+"/"+header.countpathurl, urlParameters);
    		  	resultobj = new JSONObject(Jsonstring);
    		  	if(resultobj.getString(header.Result_key).equals(header.Success) && resultobj.getInt(header.count_key) != 1){
    		  		count = 0;
    		  		error = "404 Action forbiden. 2";
    		  	}
    		  	
    		  	if(count == 1){
    		  		insertobj.put(header.itemid_key,itemid);
    		  		insertobj.put(header.Username_key,username);
    		  		insertobj.put(header.bidvalue_key,value);
    		  		insertobj.put(header.Result_key,header.Fail);
    		  		insertobj.put(header.deleted,header.Fail);
    		  		urlParameters.clear();
    		  		urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBbids));
    		  		urlParameters.add(new BasicNameValuePair(header.Insertstring_key, insertobj.toString()));
    		  		Jsonstring = posttoClient.connect(header.Microserviceurl+"/"+header.writetodburl+"/"+header.newpathurl, urlParameters);
    		  		resultobj = new JSONObject(Jsonstring);
    		  		if(resultobj.getString(header.Result_key).equals(header.Success)){
    		  			output.put(header.Result_key, header.Success);
    		  		}else{
						output.put(header.Result_key, header.Fail);
						output.put(header.error_key, "Internal error");
    		  		}
    		  	}else{
    		  		output.put(header.Result_key, header.Fail);
					output.put(header.error_key, error);
    		  	}
    	  	} catch (Exception e) {
    	  		output.put(header.Result_key, header.Fail);
    	  		e.printStackTrace();
    	  	}
    	  return output.toString();
      }
}