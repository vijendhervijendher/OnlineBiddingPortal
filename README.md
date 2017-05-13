# OnlineBiddingPortal
Responsive website

Technologies and Tools used - Eclipse, JSP, Bean, Jersey, MongoDB, Boot strap


Responsive and SOA based bidding portal comprising of Website, web services(obsservice) and micro services.
Website is run on one Tomcat server while the other Tomcat server hosting two web apps - one for web services and the other for micro services.
Website is built using JSPs and Beans and the purpose is to render the web pages to the user and redirecting to the web services for any back end functionality.
Web services are used for all the logic and micro services for CRUD operations on the data base(MongoDB). Both web apps are built using the Jersey frame work.

Instructions for executing the project
1. Access the zip from https://drive.google.com/file/d/0B8ZLRYk2pU-tNElVaG1yZzY0dmM/view?usp=sharing and 01-05-Update 1 folder
2. Export website war file into eclipse and change website url to port 8080 with http protocol in header class(or you can set up tls/ssl for port 8443)
3. Similarly change microserviceurl in obsservice/header class
4. Export website war file into one tomcat server with (http and 8080) or (https and 8443)
5. Export obsservice and microservice into other tomcat server with (http 8090) or (https and 8444)
6. Start the servers and access the web site
7. WinnerDeclarationJob in the winner package is a java application when run sends email to the winner and the owner of the product.
