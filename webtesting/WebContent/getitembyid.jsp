<%@page import="javax.swing.text.Document"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="com.sourcecode.example.gettoClient"%>
<%@page import="org.json.simple.*"%>
<%@page import="com.sourcecode.example.header"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="gib" scope="session"
	class="com.sourcecode.example.getitembyidBean" />
 
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

    <title>You can check your item.</title>

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

   
        <%
			String resultstring = gib.getitem(request.getParameter("id"));
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(resultstring);
			String msg = (String) json.get("result_obj");
			JSONObject jsn = (JSONObject) parser.parse(msg);
			String html = "";
			System.out.println(jsn.get(header.pname_key));
			System.out.println(jsn.get(header.Base_key));
        	
			
			String appendtext="";
			appendtext += "<div id=\"carousel-example-generic\" class=\"carousel slide\" data-ride=\"carousel\">"+
			                 "<ol class=\"carousel-indicators\">"; 
			appendtext +=  "<li data-target=\"#carousel-example-generic\" data-slide-to=\"0\" class=\"active\"></li>";
			for(int i =0;i< 3;i++){
			    if(i != 0){
			    	appendtext += "<li data-target=\"#carousel-example-generic\" data-slide-to=\""+i+"\" class=\"\"></li>";
			    }
			}
			appendtext += "</ol>";
			appendtext +=  "<div class=\"carousel-inner\" role=\"listbox\">";
			appendtext += "<div class=\"item active\">"+
			             "<img style=\"margin:auto;\"  alt=\"First slide [1140x500]\" src=\""+header.dummyimg+
		                 "\" height=\"500\" width=\"500\" >"+"</div>";
		    for(int i = 0;i < 3;i++){
			    if(i != 0){
			    	appendtext += "<div class=\"item\">"+
		            "<img style=\"margin:auto;\"  alt=\"First slide [1140x500]\" src=\""+header.dummyimg+
		            "\" height=\"500\" width=\"500\" >"+"</div>";
			    }
			}
		    
		    appendtext +=  "<a class=\"left carousel-control\" href=\"#carousel-example-generic\" role=\"button\" data-slide=\"prev\">"+
		    	    "<span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>"+
		    	    "<span class=\"sr-only\">Previous</span>"+"</a>";
		    	    appendtext +=  
		    	    "<a class=\"right carousel-control\" href=\"#carousel-example-generic\" role=\"button\" data-slide=\"next\">"+
		    	    "<span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>"+
		    	    "<span class=\"sr-only\">Next</span>"+"</a>"+"</div>";
		    	    
		    	    appendtext += "<div class=\"page-header\">";
		    	    appendtext += "<h1 style=\"margin:auto;\">" +"<span class=\"label label-default\">Base:<b>"+ jsn.get(header.Base_key) + "</b></span>";
		    	    appendtext += "<span id=\"bidvalue\" class=\"label label-primary\">Place Bid:$<b>" +"</b></span>" + "<input id=\"bid\" type=\"text\"></input>";
		    	    appendtext +=  "<button  onclick=\"myfunction()\">"+"Submit!</button>";
		    	    appendtext +="</h1></div>";
		    	    appendtext +=  "<div class=\"well\">";
		    	    appendtext += "<h1>Description:</h1>";
		    	    appendtext += "<p>" +jsn.get(header.Descriptio_key)+ "</p>";
		    	    appendtext += "</div>";

        
        %>
        
        <div id="itemholder" class=" main">
          <h1 id="test" class="page-header"></h1>
          <%= appendtext%>
        </div>
	<script>
	function getParameterByName(name) {
	    var url = window.location.href;
	    name = name.replace(/[\[\]]/g, "\\$&");
	    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
	        results = regex.exec(url);
	    if (!results) return null;
	    if (!results[2]) return '';
	    return decodeURIComponent(results[2].replace(/\+/g, " "));
	}
	    function myfunction(){
	    	var value=document.getElementById("bid").value;
	    	
	    	var url = "checkout.jsp"+"?";
	    	url += "bid="+value+"&";
	    	url += "id="+getParameterByName("id");
	    	window.location.href = url;
	    }
		
		
	</script>
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