


//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import javax.swing.text.html.parser.Entity;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//public class WinnerDeclarationJob {
//	public static void main(String[] args){
//		JSONParser parser = new JSONParser();
//		JSONObject output = new JSONObject();
//		JSONArray jArray = new JSONArray();
//		HttpClient client;
//		client =  HttpClientBuilder.create().build();
//		HttpGet get = new HttpGet("https://localhost:8444/obsservice/winnerService");
//		HttpPost post = new HttpPost("https://localhost:8444/obsservice/winnerService");
//		try {
//			HttpResponse response = client.execute(get);
//			String productList = EntityUtils.toString(response.getEntity());
//			String outputstring = (String) parser.parse(productList);
//			output = new JSONObject(outputstring);
//			String jarray = (String) output.get(header.Resultobj_key);
//			jArray = new JSONArray(jarray);
//			for(int i=0;i < jArray.length();i++){
//				String json = (String) jArray.get(i);
//				JSONObject jObject = new JSONObject(json);
//				long presentTime = new Date().getTime();
//				if((presentTime - (long)jObject.get("time")) > TimeUnit.MILLISECONDS.toMillis(1)){
//					List<NameValuePair> urlParams = new ArrayList<>();
//					urlParams.add(new BasicNameValuePair(header.itemid_key, (String) jObject.get(header.itemid_key)));
//					post.setEntity(new UrlEncodedFormEntity(urlParams));
//					client.execute(post);
//				}
//			}
//		} catch (ParseException | IOException | org.json.simple.parser.ParseException e) {
//			e.printStackTrace();
//		}
//	}
//}






//
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;


public class WinnerDeclarationJob{

		public static void main(String[] args){
				Timer timer = new Timer();
			
				timer.schedule(new WinnerDeclaration(), WinnerDeclaration.DELAY, WinnerDeclaration.DELAY);
}
}
 class WinnerDeclaration extends TimerTask{
	
	
	public static final int DELAY = 30*1000;
	

	@Override
	public void run() {
		executeTask();
		
	}
	
	public void executeTask(){
		JSONParser parser = new JSONParser();
		JSONObject output = new JSONObject();
		JSONArray jArray = new JSONArray();
		HttpClient client;
		client =  HttpClientBuilder.create().build();
		HttpGet get = new HttpGet("https://localhost:8444/obsservice/winnerService");
		HttpPost post = new HttpPost("https://localhost:8444/obsservice/winnerService");
		try {
			HttpResponse response = client.execute(get);
			String productList = EntityUtils.toString(response.getEntity());
			String outputstring = (String) parser.parse(productList);
			output = new JSONObject(outputstring);
			String jarray = (String) output.get(header.Resultobj_key);
			jArray = new JSONArray(jarray);
			for(int i=0;i < jArray.length();i++){
				String json = (String) jArray.get(i);
				JSONObject jObject = new JSONObject(json);
				long presentTime = new Date().getTime();
				if((presentTime - (long)jObject.get("time")) > TimeUnit.MILLISECONDS.toMillis(1)){
					List<NameValuePair> urlParams = new ArrayList<>();
					urlParams.add(new BasicNameValuePair("itemid", (String) jObject.get("itemid")));
					post.setEntity(new UrlEncodedFormEntity(urlParams));
					client.execute(post);
				}
			}
		} catch (ParseException | IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}
}