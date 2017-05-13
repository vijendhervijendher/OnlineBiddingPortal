/**
 * This page contains script for loading items with Ajax
 */
var itemsLoading = 1;
var start = 0;
var loadmoreid = "loadnew";
var getmoreid = "#loadnew";
var appendText = "";
var getid="";
var id = start.toString();
var item;
function loadDoc(url, cFunction) {
		var xhttp;
		xhttp=new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
                                  if (this.readyState == 4 && this.status == 200) {
                                     cFunction(this);
                                }
		};
		xhttp.open("GET", url, true);
		xhttp.send();
	}
function getParameterByName(name) {
    var url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
function loadItems(xhttp) {
	appendText = "";
	getid="";
	id = start.toString();
	var responsetext = xhttp.responseText;
	var itemArray = JSON.parse(responsetext);
	if(itemsLoading !=1){
		$("#more").remove();
	}else{
		id = start.toString();
		appendText = '<div id='+id+' class="row placeholders"></div>';
		$("#itemholder").append(appendText);
	}
	for(item of itemArray){
		if(start!=0 && start%4 == 0){
			id = start.toString();
			appendText = '<div id='+id+' class="row placeholders"></div>';
			$("#itemholder").append(appendText);
		}
		getid= "#"+id;
		var itemURL = "localhost:8080/webtesting/getitem.jsp?itemid="+item.id;
		appendText =  '<div class="col-xs-6 col-sm-3 placeholder">' +
                  '<img src=\"'+item.img+'\" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">'+
				  '<h4>'+'<a href=\"'+itemURL+'\">'+item.lable+'</a></h4>'+
                  '<span class="text-muted">'+'<b>Base:$</b>'+item.base+'<b>Current:$</b>'+item.latest+'</span>' +
                  '</div>';
		$(getid).append(appendText);
		start++;
	}
	if((start)%4 == 0){
		id = start.toString();
		appendText = '<div id='+id+' class="row placeholders"></div>';
		$("#itemholder").append(appendText);
		getid= "#"+id;
			appendText = "<div id='more' class='col-xs-6 col-sm-3 placeholder'><input type='button'  value='load more' onclick='loadDoc('http://localhose:8000/serveitems/items.jsp'+'?itemsLoading='+itemsLoading, loadItems);' ></input></div>"
        $(getid).append(appendText);		
	}else{
		var temp = (Math.ceil(start/4)-1)*4;
		id = temp.toString(); 
		getid= "#"+id;
		appendText = "<div id='more' class='col-xs-6 col-sm-3 placeholder'><input type='button'  value='load more' onclick='loadDoc('http://localhose:8000/serveitems/items.jsp'+'?itemsLoading='+itemsLoading, loadItems);' ></input></div>"
        $(getid).append(appendText);
	} 
}

function mainpage() {	    
   loadDoc("http://localhost:8080/webtesting/items.jsp"+"?itemsLoading="+itemsLoading, loadItems);
}
function loaditempage(){
	loadDoc("http://localhost:8080/webtesting/itembyid.jsp?itemid="+getParameterByName("itemid"), loadItem);	
}
function loadItem(xhttp){
	var i = 0;
	appendtext="";
	var itemtext = xhttp.responseText;
	item = JSON.parse(itemtext);
	var imgArray = item.img;
	appendtext = '<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">'+
	                 '<ol class="carousel-indicators">'; 
	appendtext = appendtext + '<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>';
	for(var img of imgArray){
	    if(i != 0){
	    	appendtext = appendtext + '<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class=""></li>';
	    }
	    i++;
	}
	appendtext = appendtext + '</ol>';
	appendtext = appendtext + '<div class="carousel-inner" role="listbox">';
	appendtext = appendtext + '<div class="item active">'+
	             '<img style="margin:auto;"  alt="First slide [1140x500]" src="'+imgArray[0]+
                 '" height="500" width="500" >'+'</div>';
    i = 0;
    for(var img of imgArray){
	    if(i != 0){
	    	appendtext = appendtext + '<div class="item">'+
            '<img style="margin:auto;"  alt="First slide [1140x500]" src="'+imgArray[i]+
            '" height="500" width="500" >'+'</div>';
	    }
	    i++;
	}
    appendtext = appendtext + '<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">'+
    '<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>'+
    '<span class="sr-only">Previous</span>'+'</a>';
    appendtext = appendtext + 
    '<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">'+
    '<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>'+
    '<span class="sr-only">Next</span>'+'</a>'+'</div>';
    appendtext = appendtext + '<div class="page-header">';
    appendtext = appendtext + '<h1 style="margin:auto;">' + '<span class="label label-default">Base:<b>'+ item.base +'</b></span>'+
                 '<span class="label label-success">highest Bid:$<b>'+ item.current +'</b></span>';
    appendtext = appendtext + '<span class="label label-primary">Place Bid:$<b>'+'</b></span>'+'<input id="bid" type="text"></input>';
    appendtext = appendtext + '<button id="submit" type="button" class="btn btn-lg btn-default">Submit!</button>';
    appendtext = appendtext + '<button id="cart" type="button" onclick="addtocart();" class="btn btn-lg btn-default">Add to cart!</button>';
    appendtext = appendtext + '</h1></div>';
    appendtext = appendtext + '<div class="well">';
    appendtext = appendtext + '<h1>Description:</h1>';
    appendtext = appendtext + '<p>'+item.desc+'</p>'
    appendtext = appendtext + '</div>'
	$("#test").text(item.lable);
	$('#itemholder').append(appendtext);
}

function getUser(){
	return "wpl";
}
function addtocart(){
	document.cookie = "item="+getUser()+"; expires=1111111111111111111111111111111111; path=/;item="+item.itemid;
}







