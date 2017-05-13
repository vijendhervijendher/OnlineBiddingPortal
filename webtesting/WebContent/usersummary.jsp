<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.zip.DataFormatException"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.*"%>
<%@page import="com.sourcecode.example.header"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="sbean" scope="session"
	class="com.sourcecode.example.summeryBean" />
<jsp:useBean id="uBean" scope="session"
	class="com.sourcecode.example.updateUserDetailsBean" />      
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="ecommerec,ebidding,eauction,bidding">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/favicon.ico">

    <title>Welcome.! Buy or sell items by online bidding.</title>

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com//dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="https://getbootstrap.com//assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet">

    <script src="https://getbootstrap.com//assets/js/ie-emulation-modes-warning.js"></script>
    
    <style>
   tr:hover {
    background-color: purple;
}

tr:hover td {
    background-color: transparent; /* or #000 */
}
    
    </style>

  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Online Bidding</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
          <%if(session.getAttribute("auth") != null){%>
            <li class="active"><a href="main.jsp">Home</a></li>
            <li class="active"><a href="usersummary.jsp">User Summary</a></li>
            <li class="active"><a href="productssummary.jsp">Products Summary</a></li>
            <li class="active"><a href="bidssummary.jsp">BIDS Summary</a></li>
            <li class="active"><a href="UploadItem.jsp">Upload Item</a></li>
          
            <li><a href="logout.jsp">Logout</a></li>
            
            <%} else {%>
            
            <li><a href="login.jsp">Login</a></li>
            <li><a href="signup.jsp">Sign Up</a></li>
            
            <%} %>
   
          </ul>

      
         </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">

		<%
		
		if(request.getMethod().equalsIgnoreCase("post")) {
			uBean.update((String)request.getParameter(header.Fname_key), 
					(String)request.getParameter(header.Lname_key), 
					(String)session.getAttribute(header.Username_key), 
					(String)request.getParameter(header.email_key), 
					(String)request.getParameter(header.Password_key), 
					(String)request.getParameter(header.Street_key), 
					(String)request.getParameter(header.City_key), 
					(String)request.getParameter(header.State_key), 
					(String)request.getParameter(header.Phone_key), 
					(String)request.getParameter(header.Zip_key));
		
		} 
			String result = sbean.getSummery((String)session.getAttribute(header.Username_key));
        	JSONObject resultobj;
        	JSONObject user_detailsobj;
        	JSONArray product_array;
        	JSONArray bid_obj;
        	JSONParser parser = new JSONParser();
        	resultobj = (JSONObject) parser.parse(result);
        	user_detailsobj = (JSONObject)parser.parse((String)resultobj.get(header.DBuser_details));
        	product_array = (JSONArray)parser.parse((String)resultobj.get(header.DBproduct_details));
        	bid_obj = (JSONArray)parser.parse((String)resultobj.get(header.DBbids));
        	System.out.println(user_detailsobj);
        	System.out.println(product_array);
        	System.out.println(bid_obj);
        	DateFormat df = new SimpleDateFormat(); 
        %>        
        
        <div id="itemholder" class=" main">
          	<h2 class="sub-header">USER DETAILS</h2>
			<div class="table-responsive">
			<form method="post"  action="usersummary.jsp">
            <table class="table table-striped">
              <tbody>
                <tr>
                  <td><%= header.Fname_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.Fname_key)+"\"" %> name=<%="\""+header.Fname_key+"\"" %>/></td>
                </tr>
                <tr>
                  <td><%= header.Lname_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.Lname_key)+"\"" %> name=<%="\""+header.Lname_key+"\"" %>/></td>
                </tr>
                <tr>
                  <td><%= header.Username_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.Username_key)+"\"" %> name=<%="\""+header.Username_key+"\"" %>/></td>
                </tr>
                <tr>
                  <td><%= header.email_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.email_key)+"\"" %> name=<%="\""+header.email_key+"\"" %>/></td>
                </tr>                                
                <tr>
                  <td><%= header.Password_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.Password_key)+"\"" %> name=<%="\""+header.Password_key+"\"" %>/></td>
                </tr>
                <tr>
                  <td><%= header.Street_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.Street_key)+"\"" %> name=<%="\""+header.Street_key+"\"" %>/></td>
                </tr>
                <tr>
                  <td><%= header.Phone_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.Phone_key)+"\"" %> name=<%="\""+header.Phone_key+"\"" %>/></td>
                </tr>

                <tr>
                  <td><%= header.City_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.City_key)+"\"" %> name=<%="\""+header.City_key+"\"" %>/></td>
                </tr>
                <tr>
                  <td><%= header.State_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.State_key) +"\"" %> name=<%="\""+header.State_key+"\"" %>/></td>
                </tr>
                <tr>
                  <td><%= header.Zip_key %></td>
                  <td><input type="text" placeholder=<%= "\""+user_detailsobj.get(header.Zip_key)+"\"" %> name=<%="\""+header.Zip_key+"\"" %>/></td>
                </tr>
                <tr>
                  <td><%= "Last login time" %></td>
                  <td><%= user_detailsobj.get(header.lastlogin_key) %></td>
                </tr>
                <tr>
                  <td></td>
                  <td><input type="submit" value="submit"/></td>
                </tr>
              </tbody>
            </table>
            </form>
          </div>
         </div> 
      </div>
      </div>    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://getbootstrap.com//dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="https://getbootstrap.com//assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="https://getbootstrap.com//assets/js/ie10-viewport-bug-workaround.js"></script>
    <script>   
    </script>    
  </body>
</html>