package microservice;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;


//Checks a given username is available. 
// IF available result is 1 else 0.

@Path(header.chechuserurl)
public class checkavailability {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String chechusername(@FormParam(header.Username_key) String username){
		int count = 0;
		JSONObject output = new JSONObject();
		
		try {
			DBCollection collection = DBconnection.db.getCollection(header.DBuser_details);
			BasicDBObject document = new BasicDBObject();
			document.put(header.Username_key, username.toLowerCase());
			System.out.println(document.toString());
			count = collection.find(document).count();
		} catch (Exception e) {
			
			e.printStackTrace();
			count = 0;
		}
		
		if(count == 0)
			output.put(header.Result_key, header.Success);
		else
			output.put(header.Result_key, header.Fail);
		
		return output.toString();
	}
}
