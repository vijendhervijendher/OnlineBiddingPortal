<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="com.sourcecode.example.gettoClient"%>
<%@page import="org.json.simple.*"%>
<%@page import="com.sourcecode.example.header"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="gi" scope="session"
	class="com.sourcecode.example.getItems" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Product Page</title>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../../assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dashboard.css" rel="stylesheet">
<script type="text/javascript" src="sorttable.js"></script>
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/assets/js/ie-emulation-modes-warning.js"></script>
<script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<script language="javascript" type="text/javascript" src="actb.js"></script>
<!-- External script -->
<script language="javascript" type="text/javascript"
	src="tablefilter.js"></script>
<script src="http://code.jquery.com/jquery-latest.js">
	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<style>
th {
	background-color: #003366;
	color: #FFF;
	padding: 2px;
	border: 1px solid #ccc;
	table-layout: fixed;
}

html, body {
	height: 100%;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Bidding Stuff</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="main.jsp">Home</a></li>
					<!-- <li><a href="ProfilePage.jsp">Profile</a></li> -->
					<li><a href="signup.jsp">Sign Up</a></li>
					<li><a href="login.jsp">Login</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>


	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<!-- <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Analytics</a></li>
            <li><a href="#">Export</a></li>
          </ul> -->
				<br>
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
					<li><a href=""> </a></li>
				</ul>
			</div>

			<br> <br> <br> <br> <br> <br>
			<div class="col-xs-6 col-sm-3 placeholder">
				<%
				String array = gi.items();
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(array);
				String msg = (String) json.get("result_obj");
				JSONArray jsn = (JSONArray) parser.parse(msg);
				for (int i = 0; i < jsn.size(); i++) {
					String res = (String)jsn.get(i);
					JSONObject obj = (JSONObject) parser.parse(res);
					out.println("item id : " + obj.get("itemid"));
					
					
				}
			%>
					
			</div>




			<!-- Bootstrap core JavaScript
    ================================================== -->
			<!-- Placed at the end of the document so the pages load faster -->
			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
			<script>
				window.jQuery
						|| document
								.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
			</script>
			<script src="../../dist/js/bootstrap.min.js"></script>
			<script
				src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
				integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
				crossorigin="anonymous"></script>

			<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
			<script src="../../assets/js/vendor/holder.min.js"></script>
			<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
			<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

		</div>
	</div>
	</div>
	</div>

</body>


</html>