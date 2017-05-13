<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="com.sourcecode.example.posttoClient"%>
<%@page import="org.json.simple.*"%>
<%@page import="com.sourcecode.example.header"%>

    
<jsp:useBean id="co" scope="session"
	class="com.sourcecode.example.checkoutBean" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    
    <link rel="icon" href="../../favicon.ico">

    <title>CheckOut Page</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="cover.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

   
</head>
<body>

 
 <%		
 		if(session.getAttribute("auth") != null){
			HashMap<String,String> output = co.checkout(request.getParameter("id"), request.getParameter("bid"), (String)session.getAttribute(header.Username_key));
 	 	} else{ 
 	 		response.sendRedirect("login.jsp");
 	 	}
 	 	
 %>
 
 <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
             <!--  <h3 class="masthead-brand">Check-Out Page</h3> -->
              <nav>
                <ul class="nav navbar-nav navbar-right">

            <li class="active"><a href="main.jsp">Home</a></li><br>
            <li class="active"><a href="usersummary.jsp">User Summary</a></li><br>
            <li class="active"><a href="productssummary.jsp">Products Summary</a></li><br>
            <li class="active"><a href="bidssummary.jsp">BIDS Summary</a></li><br>
                        <li class="active"><a href="UploadItem.jsp">Upload Item</a></li>
            
          </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">Your BID has successfully been placed.</h1>
            <p class="lead">The winners will be mailed later. </p>
            <p class="lead"> Till then you can check for more BIDS on our: 
              <a href="main.jsp" class="btn btn-lg btn-default">Main Page</a>
            </p>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>Developed by VDK. </a></p>
            </div>
          </div>

        </div>

      </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>


 
</body>
</html>