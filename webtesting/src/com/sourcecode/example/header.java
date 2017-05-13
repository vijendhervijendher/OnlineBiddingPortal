package com.sourcecode.example;

public class header {

	
    public static String DBuser_details = "user_details";
    public static String DBproduct_details = "product_details";
    public static String DBitems = "items";
    public static String DBtime = "time";
    public static String DBbids = "bids";
	
    public static final String Readurl = "read";
    public static final String countpathurl = "count";
    public static final String readonepathurl = "one";
    public static final String readmanypathurl = "many";
    public static final String chechuserurl = "checkusername";
    public static final String writetodburl = "writetodb";
    public static final String newpathurl = "new";
    public static final String updatepathurl = "update";                             
    public static final String catalogueurl = "catalogue";
    public static final String Signupurl = "signup";
    public static final String Uploadurl = "upload";
    public static final String Loginurl = "login";
    public static final String updatepath_optionsurl = "update_options";
    public static final String Username_key = "username";
    public static final String Collection_key= "collection";
    public static final String Insertstring_key= "insert_string";
    public static final String Querystring_key= "query_string"; 
    public static final String Updatestring_key="update_string";
    public static final String Resultobj_key="result_obj";
    public static final String email_key="email";
    public static final String Fname_key="first_name";
    public static final String Lname_key="last_name";
    public static final String Phone_key="phone";
    public static final String Password_key="password";
    public static final String Street_key="street";
    public static final String City_key="city";
    public static final String State_key="state";
    public static final String Zip_key="zip";
    public static final String count_key="count";
    public static final String pname_key="productname";   
    public static final String Descriptio_key="description";
    public static final String Base_key="baseprice";
    public static final String itemid_key="itemid";
    public static final String bidvalue_key="value";
    public static final String error_key="error";
    public static final String lastlogin_key="time";
    public static final String ended = "ended";
    public static final String date = "date";


    
    public static final String websiteurl = "https://localhost:8443";
    public static final String websitepathurl = "webtesting";
    public static final String signupurl =websiteurl+"/"+websitepathurl+"/"+"signup.jsp";
    public static final String loginurl =websiteurl+"/"+websitepathurl+"/"+"login.jsp";
    public static final String mainurl =websiteurl+"/"+websitepathurl+"/"+"main.jsp";
    public static final String checkouturl =websiteurl+"/"+websitepathurl+"/"+"checkout.jsp";
    public static final String bidsurl =websiteurl+"/"+websitepathurl+"/"+"productbids.jsp";
    public static final String getitembyidurl =websiteurl+"/"+websitepathurl+"/"+"getitembyid.jsp";
    
    
    //services
    public static final String servicesurl = "https://localhost:8444";
    public static final String servicespath = "obsservice";
    
    public static final String producturl = "product";
    public static final String profileurl = "profile";
    public static final String getproductserviceurl = servicesurl+"/"+servicespath+"/"+producturl;
    public static final String getsummeryurl = servicesurl+"/"+servicespath+"/"+profileurl;
    public static final String signupserviceurl = servicesurl+"/"+servicespath+"/"+Signupurl;
    public static final String loginserviceurl = servicesurl+"/"+servicespath+"/"+Loginurl;
    public static final String catalogueserviceurl = servicesurl+"/"+servicespath+"/"+catalogueurl;
    public static final String dummyimg = "http://i-cdn.phonearena.com/images/phones/42880-specs/Apple-iPhone-5c.jpg";
    public static final String itembidsserviceurl = servicesurl+"/"+servicespath+"/"+"bids";
    public static final String checkoutserviceserviceurl = servicesurl+"/"+servicespath+"/"+"checkout";
    public static final String searchserviceurl = servicesurl+"/"+servicespath+"/"+"search";
    public static final String uploadserviceurl = servicesurl+"/"+servicespath+"/"+"upload";
    
    public static final String deletebidserviceurl = servicesurl+"/"+servicespath+"/"+"deletebid";
    public static final String updateserviceurl = servicesurl+"/"+servicespath+"/"+"update";    
    public static final String deleteMybidserviceurl = servicesurl+"/"+servicespath+"/"+"deletemyBid";
    
    
    public static String Result_key = "result";
    public static String Success = "1";
    public static String Fail = "0";	
    
    public header(){
    	
    }
    
    
}
