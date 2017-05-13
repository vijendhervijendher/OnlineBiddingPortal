package microservice;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DBconnection {
	 	
	
	public static MongoClient mdb = new MongoClient(header.DBurl);
	@SuppressWarnings("deprecation")
	public static DB db = mdb.getDB(header.DBstring);
	    
}
