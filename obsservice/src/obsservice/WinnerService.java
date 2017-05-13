package obsservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import mail.EmailService;

@Path("winnerService")
public class WinnerService {

	String Jsonstring;
	private JSONObject resultobj;
	private JSONObject productResult;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String expiredProductDetails(){
		HttpClient client;
		client =  HttpClientBuilder.create().build();
		JSONObject queryobject = new JSONObject();
		queryobject.put(header.ended, header.Fail);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBproduct_details));
		urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
		try {			
			Jsonstring = posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readmanypathurl,urlParameters);
			resultobj = new JSONObject(Jsonstring);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultobj.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateExpiredProducts(@FormParam(header.itemid_key) String itemid){
		JSONObject queryobject = new JSONObject();
		JSONObject queryObjectForItem = new JSONObject();
		JSONObject updatequeryobject = new JSONObject();
		JSONObject getEmailQueryObject = new JSONObject();
		JSONObject jsonStringToObject = new JSONObject();
		JSONObject result = new JSONObject();
		JSONObject getEmailQueryOwnerObject = new JSONObject();
		
		String mailProductWinner;
		String mailProductBasePrice;
		String mailProductWinPrice;
		String mailProductName;
		String mailPostedOwner;
		String mailOwnerEmail = "vijendherreddy2379@gmail.com";
		
		String resultJson = "";
		queryobject.put(header.itemid_key,itemid);
		updatequeryobject.put(header.ended, header.Success);
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair(header.Collection_key, header.DBproduct_details));
		urlParameters.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
		urlParameters.add(new BasicNameValuePair(header.Updatestring_key, updatequeryobject.toString()));
		posttoClient.connect(header.Microserviceurl + "/" + header.writetodburl + "/" + header.updatepath_optionsurl, urlParameters);
		
		
		List<NameValuePair> urlParamsForItemId = new ArrayList<>();
		urlParamsForItemId.add(new BasicNameValuePair(header.Querystring_key, queryobject.toString()));
		urlParamsForItemId.add(new BasicNameValuePair(header.Collection_key, header.DBproduct_details));
		Jsonstring = posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readonepathurl, urlParamsForItemId);
		jsonStringToObject = new JSONObject(Jsonstring);
		resultJson = (String) jsonStringToObject.get(header.Resultobj_key);
		productResult = new JSONObject(resultJson);
		String itemId = (String) productResult.get(header.itemid_key);
		mailProductName = (String) productResult.get(header.pname_key);
		mailProductBasePrice = (String) productResult.get(header.Base_key);
		mailPostedOwner = (String) productResult.get(header.Username_key);
		
		if(mailPostedOwner != null){
			List<NameValuePair> urlParamsforOwnerEmail = new ArrayList<>();
			getEmailQueryOwnerObject.put(header.Username_key, mailPostedOwner);
			urlParamsforOwnerEmail.add(new BasicNameValuePair(header.Collection_key, header.DBuser_details));
			urlParamsforOwnerEmail.add(new BasicNameValuePair(header.Querystring_key, getEmailQueryOwnerObject.toString()));
			Jsonstring = posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readonepathurl, urlParamsforOwnerEmail);
			jsonStringToObject = new JSONObject(Jsonstring);
			resultJson = (String) jsonStringToObject.get(header.Resultobj_key);
			result = new JSONObject(resultJson);
			mailOwnerEmail = result.getString(header.email_key);
		}
		
		List<NameValuePair> urlParamsForUserName = new ArrayList<>();
		urlParamsForUserName.add(new BasicNameValuePair(header.Collection_key, header.DBbids));
		queryObjectForItem.put(header.itemid_key, itemId);
		queryObjectForItem.put(header.deleted, header.Fail);
		urlParamsForUserName.add(new BasicNameValuePair(header.Querystring_key, queryObjectForItem.toString()));
		Jsonstring =posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readmanypathurl, urlParamsForUserName);
		jsonStringToObject = new JSONObject(Jsonstring);
		String jarray = (String) jsonStringToObject.get(header.Resultobj_key);
		JSONArray jArray = new JSONArray(jarray);
		
		if(jArray.length() > 0){
		int value = 0;
		String username = null;
		for(int i=0;i < jArray.length();i++){
			String json = (String) jArray.get(i);
			JSONObject jObject = new JSONObject(json);
			String bidValue = (String) jObject.get(header.bidvalue_key);
			int integerBidValue = Integer.parseInt(bidValue);
			if(integerBidValue > value){
				value = integerBidValue;
				username = (String) jObject.get(header.Username_key);
			}
		}
		mailProductWinPrice = String.valueOf(value);
		mailProductWinner = username;
		JSONObject updateQueryObjectForResult =  new JSONObject();
		JSONObject queryObjectForResultSetting = new JSONObject();
		queryObjectForResultSetting.put(header.Username_key,username);
		updateQueryObjectForResult.put(header.Result_key, header.Success);
		List<NameValuePair> urlParametersForResultSetting = new ArrayList<>();
		urlParametersForResultSetting.add(new BasicNameValuePair(header.Collection_key, header.DBbids));
		urlParametersForResultSetting.add(new BasicNameValuePair(header.Querystring_key, queryObjectForResultSetting.toString()));
		urlParametersForResultSetting.add(new BasicNameValuePair(header.Updatestring_key, updateQueryObjectForResult.toString()));
		posttoClient.connect(header.Microserviceurl + "/" + header.writetodburl + "/" + header.updatepath_optionsurl, urlParametersForResultSetting);
		
		if(username != null){
		List<NameValuePair> urlParamsforEmail = new ArrayList<>();
		getEmailQueryObject.put(header.Username_key, username);
		urlParamsforEmail.add(new BasicNameValuePair(header.Collection_key, header.DBuser_details));
		urlParamsforEmail.add(new BasicNameValuePair(header.Querystring_key, getEmailQueryObject.toString()));
		Jsonstring = posttoClient.connect(header.Microserviceurl + "/" + header.Readurl + "/" + header.readonepathurl, urlParamsforEmail);
		jsonStringToObject = new JSONObject(Jsonstring);
		resultJson = (String) jsonStringToObject.get(header.Resultobj_key);
		result = new JSONObject(resultJson);
		String emailWinner = result.getString(header.email_key);
		
		EmailService emailService = new EmailService();
		emailService.setEmailTo(emailWinner);
		emailService.setEmailFrom("wplmithun@gmail.com");
		emailService.setHost("smtp.gmail.com");
		emailService.setProperties();
		emailService.setSession();
		
		String subject = "Online Bidding successful registration";
		String msg = "Congrats" + "  " + mailProductWinner + "   " + "you have won the bidding of the following product :" + "\n" + "product name :" + mailProductName + "\n" + "product base price :" + mailProductBasePrice + "\n" + "your aquired the bid for :" + mailProductWinPrice;
		System.out.println(msg);
		emailService.sendEmail(subject, msg);
		
		EmailService emailServiceToUser = new EmailService();
		emailServiceToUser.setEmailTo(mailOwnerEmail);
		emailServiceToUser.setEmailFrom("wplmithun@gmail.com");
		emailServiceToUser.setHost("smtp.gmail.com");
		emailServiceToUser.setProperties();
		emailServiceToUser.setSession();
		
		String userSubject = "Uploaded item has bee sold";
		String messageToUser = "Congrats" + "  "  + mailPostedOwner + "  "  + "Your item has been sold" + "\n" + "product name :" + mailProductName + "\n" + "base price :" + mailProductBasePrice + "\n" + "winner : " + mailProductWinner + "\n" + "final selling price : " + mailProductWinPrice;
		System.out.println(messageToUser);
		emailServiceToUser.sendEmail(userSubject, messageToUser);
		}
		}
	}
	
}
