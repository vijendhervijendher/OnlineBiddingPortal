<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="com.sourcecode.example.gettoClient"%>
<%@page import="org.json.simple.*"%>
<%@page import="com.sourcecode.example.header"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="searchbean" scope="session"
	class="com.sourcecode.example.searchBean" />
    
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
                      <li> 
            		<form class="navbar-form navbar-right">
            					<input id="query" type="text" class="form-control" placeholder="Search here.!">
           						<input id="search" type="button" value="Search"></input>
          			</form>
            </li>
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
				String array = searchbean.searchitems((String)request.getParameter("query"));
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(array);
				String msg = (String) json.get("result_obj");
				System.out.println(msg);
				JSONArray jsn = (JSONArray) parser.parse(msg);
				String html = "";
				for (int i = 0; i < jsn.size();) {
					String res = (String)jsn.get(i);
					JSONObject obj = (JSONObject) parser.parse(res);
					if(i%4 == 0){
						html += "<div  class="+"row placeholders>";
					}
					String itemURL = header.websiteurl+"/"+header.websitepathurl+"/"+"getitembyid.jsp"+"?id="+obj.get(header.itemid_key);
					html +=  "<div class=\"col-xs-6 col-sm-3 placeholder\">" +
							"<center><img src=\"" +header.dummyimg+"\" width=\"200\" height=\"200\" class=\"img-responsive\" alt=\"Generic placeholder thumbnail\"></center>"+
						    "<center><h4>"+"<a href=\""+itemURL+"\">"+obj.get(header.pname_key)+"</a></h4></center>"+
						    "<center><span class=\"text-muted\">"+"<b>Base:$</b>"+obj.get(header.Base_key)+"</span></center>" +
			                  "</div>";
					if(i%4 == 3 || i == jsn.size()-1){
						html += "</div>";
					}
					i++;
				}
			%>
        
        
        <div id="itemholder" class="main">
          <h1 id="test" class="page-header"></h1>
          <%= html %>
        </div>
      </div>
    </div>
    <p id="test"></p>
    <p id="test1"></p>
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
      		var url = "searchresult.jsp"+"?";
      		url += "query="+$("#query").val();
      		window.location.href = url;    	  
      	});
      	
      </script>    			   
            
  </body>
</html>