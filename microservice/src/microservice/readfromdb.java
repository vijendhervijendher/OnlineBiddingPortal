package microservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;


@Path(header.Readurl)
public class readfromdb {
     
	@POST
	@Path(header.countpathurl)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String count(@FormParam(header.Collection_key) String collection_name,@FormParam(header.Querystring_key) String query_string){
		int count = 0;
		JSONObject output = new JSONObject();
		JSONObject Jsonqobj;
		DBObject DBquery;
		
		try {
			DBCollection collection = DBconnection.db.getCollection(collection_name);
			
			Jsonqobj = new JSONObject(query_string);
			DBquery = (DBObject)JSON.parse(Jsonqobj.toString()); 
			
			count =  collection.find(DBquery).count();
			output.put(header.count_key, count);
			output.put(header.Result_key, header.Success);
		} catch (Exception e) {
			e.printStackTrace();
			output.put(header.Result_key, header.Fail);
		}
		return output.toString();
	}

	@POST
	@Path(header.readonepathurl)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String readone(@FormParam(header.Collection_key) String collection_name,@FormParam(header.Querystring_key) String query_string){
		
		
		JSONObject output = new JSONObject();
		JSONObject resulobj;
		
		try {
			DBCollection collection = DBconnection.db.getCollection(collection_name);
			
			JSONObject obj = new JSONObject(query_string);
			DBObject query = (DBObject)JSON.parse(obj.toString());
			
			DBObject DBobj = collection.findOne(query);
			resulobj = new JSONObject(JSON.serialize(DBobj));
			output.put(header.Resultobj_key , resulobj.toString());
			output.put(header.Result_key, header.Success);
			
		} catch (Exception e) {
			e.printStackTrace();
			output.put(header.Result_key, header.Fail);
		}
		return output.toString();
	}

	@POST
	@Path(header.readmanypathurl)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String readmany(@FormParam(header.Collection_key) String collection_name,@FormParam(header.Querystring_key) String query_string){
		
		JSONObject resulobj;
		JSONObject Jsonobj;
		DBObject DBquery;
		DBObject DBobj;
		JSONArray jarray = new JSONArray();
		JSONObject output = new JSONObject();
		
		try {
			DBCollection collection = DBconnection.db.getCollection(collection_name);
			
			Jsonobj = new JSONObject(query_string);
			DBquery = (DBObject)JSON.parse(Jsonobj.toString());
			
			DBCursor cursor = collection.find(DBquery);
			while(cursor.hasNext()){
				DBobj = cursor.next();
				resulobj = new JSONObject(JSON.serialize(DBobj));
				jarray.put(resulobj.toString());
			}
			output.put(header.Resultobj_key , jarray.toString());
			output.put(header.Result_key, header.Success);
		} catch (Exception e) {
			e.printStackTrace();
			output.put(header.Result_key, header.Fail);
		}
		return output.toString();
	}
}
