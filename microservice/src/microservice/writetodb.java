package microservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Path(header.writetodburl)
public class writetodb {

	@Path(header.newpathurl)
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public String write(@FormParam(header.Collection_key) String collection_name,@FormParam(header.Insertstring_key) String insert_string){
		JSONObject output = new JSONObject();
		JSONObject insert;
		DBObject DBinsert;
		
		try {
			DBCollection collection = DBconnection.db.getCollection(collection_name);
			insert = new JSONObject(insert_string);		
			DBinsert = (DBObject)JSON.parse(insert.toString());
			collection.insert(DBinsert);
			output.put(header.Result_key, header.Success);
		} catch (Exception e) {
			e.printStackTrace();
			output.put(header.Result_key, header.Fail);
		}
		
		return output.toString();
	}

	
	@Path(header.updatepathurl)
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public String update(@FormParam(header.Collection_key) String collection_name,@FormParam(header.Querystring_key) String query_string
			,@FormParam(header.Updatestring_key) String update_string){

		JSONObject output = new JSONObject();
		JSONObject Jsonquery;
		JSONObject Jsonupdate;
		BasicDBObject DBquery;
		BasicDBObject DBupdate;
		
		try {
			DBCollection collection = DBconnection.db.getCollection(collection_name);
			
            Jsonquery = new JSONObject(query_string);
            DBquery = (BasicDBObject)JSON.parse(Jsonquery.toString());
			
            Jsonupdate = new JSONObject(update_string);
            DBupdate = (BasicDBObject)JSON.parse(Jsonupdate.toString());
            
            
            collection.update(DBquery, DBupdate);
			output.put(header.Result_key, header.Success);
		} catch (Exception e) {
			e.printStackTrace();
			output.put(header.Result_key, header.Fail);
		}
		
		return output.toString();
	}
	
	@Path(header.updatepath_optionsurl)
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
	public String update_options(@FormParam(header.Collection_key) String collection_name,@FormParam(header.Querystring_key) String query_string
			,@FormParam(header.Updatestring_key) String update_string){

		JSONObject output = new JSONObject();
		JSONObject Jsonquery;
		JSONObject Jsonupdate;
		BasicDBObject DBquery;
		BasicDBObject DBupdate;
		BasicDBObject DBupdate_object;
		try {
			DBCollection collection = DBconnection.db.getCollection(collection_name);
			
            Jsonquery = new JSONObject(query_string);
            DBquery = (BasicDBObject)JSON.parse(Jsonquery.toString());
			
            Jsonupdate = new JSONObject(update_string);
            DBupdate = (BasicDBObject)JSON.parse(Jsonupdate.toString());

            DBupdate_object = new BasicDBObject();
            DBupdate_object.put("$set", DBupdate);
            collection.update(DBquery, DBupdate_object, true, false);
			output.put(header.Result_key, header.Success);
		} catch (Exception e) {
			e.printStackTrace();
			output.put(header.Result_key, header.Fail);
		}
		
		return output.toString();
	}
}
