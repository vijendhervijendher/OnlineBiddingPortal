<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.*"%>
<%@page import="com.sourcecode.example.header"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="gbb" scope="session"
	class="com.sourcecode.example.getBidBean" />    
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

    <title>Welcome to ebidding. Buy or sell items by online bidding.</title>

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com//dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="https://getbootstrap.com//assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet">

    <script src="https://getbootstrap.com//assets/js/ie-emulation-modes-warning.js"></script>

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
       System.out.println((String)request.getAttribute("id"));
		String result = gbb.getbids(((String)request.getParameter("id")));
        System.out.println(result);
    	JSONParser parser = new JSONParser();
    	JSONObject resultobj = (JSONObject)parser.parse(result);
    	String msg = (String) resultobj.get("result_obj");
    	System.out.println(msg);
    	JSONArray bid_obj = (JSONArray) parser.parse(msg);
    	
    %>      
                     
        <div id="itemholder" class=" main">     
            <h2 class="sub-header">PRDUCT BID DETAILS</h2>
			<div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Item id</th>
                  <th><%= header.Username_key %></th>
                  <th><%= header.bidvalue_key %></th>
                </tr>
              </thead>
              <tbody>
              	<%
              		String url = "";
              		for(int i =0; i < bid_obj.size();i++){
    					String res = (String)bid_obj.get(i);
    					JSONObject obj = (JSONObject) parser.parse(res);
             			url += "<tr>";
              			url += "<td>"+"<a href="+header.bidsurl+"?id="+(String)obj.get(header.itemid_key)+">"+(String)obj.get(header.itemid_key)+"</a>"+"</td>";		
              			url += "<td>"+(String)obj.get(header.Username_key)+"</td>";
              			url += "<td>"+(String)obj.get(header.bidvalue_key)+"</td>";
              			
              			
              			
              			
              			url += "</tr>";	
              		}
              	%>
              	<%= url %>
              </tbody>
            </table>
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
      	$( "#search" ).click(function() {
      		console.log($(this).attr('id'));
      		var url = "searchresult.jsp"+"?";
      		url += "query="+$("#query").val();
      		window.location.href = url;    	  
      	});
      	
      	
      	
      </script>    			   
              
  </body>
</html>