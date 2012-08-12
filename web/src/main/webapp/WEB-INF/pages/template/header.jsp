<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type"
	content="application/xhtml+xml; charset=UTF-8" />
<meta name="author" content="Lukasz Luszczynski - www.luszczyk.net" />
<meta name="robots" content="index, follow" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/web/resources/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/web/resources/css/pixDisplay.css" />

<c:forEach var="j" items="${h.js}">
	<script type="text/javascript" src="/web/resources/js/${j}"></script>
</c:forEach>

<!-- <script type="text/javascript" src="/web/resources/js/pixDisplay.js"></script> -->
<script type="text/javascript">
	jQuery(document).ready(

	function() {
		pixDisplay.initialize();
		pixDisplay.assignLinks();
	}
	);
	
	$(document).ready(function(){
		  var $inp = $('input');
		  $inp.bind('click', function(e){
		    if(/Enter SQL query here/.test($inp.val()))
		      $inp.val('');
		  });
		});
</script>
<title>Multimedia Database Viewer - ${h.title}</title>
</head>
<body>
	<div class="wrap background">
		<div id="search">
			<form action="/web/query/run" method="post">
				<fieldset>
					<input type="text" name="query" class="searchField" id="searchBox"
						value="Enter SQL query here" /> <input type="submit" class="button"
						value="" />
				</fieldset>
			</form>
		</div>
		<ul id="menu">
			<li><a class="current" href="/web/index">Home</a></li>
			<li><a href="#">Settings</a></li>
		</ul>

		<div class="clear"></div>

		<div id="content">