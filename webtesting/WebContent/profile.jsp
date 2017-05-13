<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.*"%>
<%@page import="com.sourcecode.example.header"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="sbean" scope="session"
	class="com.sourcecode.example.summeryBean" />    
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
            <li><a href="logout.jsp">Logout</a></li>
            
            <%} else {%>
            
            <li><a href="login.jsp">Login</a></li>
                        <li><a href="signup.jsp">Sign Up</a></li>
            
            <%} %>
            <%if(session.getAttribute("auth") != null){%>
            <ul class="nav navbar-nav navbar-right">

            <li class="active"><a href="main.jsp">Home</a></li>
            <li class="active"><a href="usersummary.jsp">User Summary</a></li>
            <li class="active"><a href="productssummary.jsp">Products Summary</a></li>
            <li class="active"><a href="bidssummary.jsp">BIDS Summary</a></li>
                        <li class="active"><a href="UploadItem.jsp">Upload Item</a></li>
          	</ul>
          <%} %>
          </ul>

          </ul>
          <form class="navbar-form navbar-right">
           <li> <input type="text" class="form-control" placeholder="Search here.!">
           <button type="submit">Search</button>
            </li>
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Analytics</a></li>
            <li><a href="#">Export</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item</a></li>
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li><a href="">More navigation</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
          </ul>
        </div>
        <% 
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
        %>        
        
        <div id="itemholder" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          	<h2 class="sub-header">USER DETAILS</h2>
			<div class="table-responsive">
            <table class="table table-striped">
              <tbody>
                <tr>
                  <td>First Name</td>
                  <td><div contenteditable><%= user_detailsobj.get(header.Fname_key) %></div></td>
                  <td><div><input type="button" onClick="$(this).closest('tr').remove();" value="Delete"></div></td>
                </tr>
                <tr>
                  <td>Last Name</td>
                  <td><div contenteditable><%= user_detailsobj.get(header.Lname_key) %></div></td>
                  
                </tr>
                <tr>
                  <td>User Name</td>
                  <td> <%=user_detailsobj.get(header.Username_key)  %> </td>
                </tr>
                <tr>
                  <td>Email ID</td>
                  <td><%= user_detailsobj.get(header.email_key) %></td>
                </tr>                                
                <tr>
                 <tr>
                  <td>Phone Number</td>
                  <td><div contenteditable><%= user_detailsobj.get(header.Phone_key) %></div></td>
                </tr>
                <tr>
                  <td>Street Address</td>
                  <td><div contenteditable><%= user_detailsobj.get(header.Street_key) %></div></td>
                </tr>
                <tr>
                  <td>City Address</td>
                  <td><div contenteditable><%= user_detailsobj.get(header.City_key) %></div></td>
                </tr>
                <tr>
                  <td>State</td>
                  <td><div contenteditable><%= user_detailsobj.get(header.State_key) %></div></td>
                <tr>
                  <td>ZIP</td>
                  <td><div contenteditable><%= user_detailsobj.get(header.Zip_key) %></div></td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- Button -->
		<div class="form-group col-md-10">
		  <div class="col-md-6">
			<button type="submit" class="btn btn-warning submit-button" >Save</button>
			<button type="submit" class="btn btn-warning submit-button" >Cancel</button>

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