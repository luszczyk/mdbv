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
<link rel="stylesheet" type="text/css" media="screen"
	href="/web/resources/css/jquery.toastmessage.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/web/resources/css/jquery-ui-1.8.22.custom.css" />

<script type="text/javascript" src="/web/resources/js/jquery.min.js"></script>

<script type="text/javascript"
	src="/web/resources/js/jquery.toastmessage.js"></script>
<script type="text/javascript"
	src="/web/resources/js/jquery-ui-1.8.22.custom.min.js"></script>

<c:forEach var="j" items="${h.js}">
	<script type="text/javascript" src="/web/resources/js/${j}"></script>
</c:forEach>
<script type="text/javascript">
	jQuery(document).ready(

	function() {
		pixDisplay.initialize();
		pixDisplay.assignLinks();
	});

	$(document).ready(function() {
		var $inp = $('input');
		$inp.bind('click', function(e) {
			if ($inp.is('.clear'))
				$inp.val('');
		});
	});

	$(function() {
		$("input:submit, a, button", ".but").button();
		$("a", ".but").click(function() {
			return false;
		});
	});

	function dbConnTest() {
		// get the form values
		var host = $('#dbhost').val();
		var user = $('#dbuser').val();
		var port = $('#dbport').val();
		var pass = $('#dbpass').val();

		$.ajax({
			type : "POST",
			url : "/web/index/dbtest",
			data : "host=" + host + "&user=" + user + "&port=" + port
					+ "&pass=" + pass,
			success : function(response) {
				// we have the response
				$().toastmessage('showSuccessToast', response);
				$('#dbhost').val('');
				$('#dbuser').val('');
				$('#dbport').val('');
				$('#dbpass').val('');
			},
			error : function(e) {
				$().toastmessage('showErrorToast', e.responseText);
			}
		});
	}
</script>
<title>Multimedia Database Viewer - ${h.title}</title>
</head>
<body>
	<div class="wrap background">
		<div id="search">
			<form action="/web/query/run" method="post">
				<fieldset>
					<input type="text" name="query" class="searchField clear"
						id="searchBox" value="Enter SQL query here" /> <input
						type="submit" class="button" value="" />
				</fieldset>
			</form>
		</div>
		<ul id="menu">
			<li><a class="current" href="/web/index">Home</a></li>
			<li><a href="#">Settings</a></li>
		</ul>

		<div class="clear"></div>

		<div id="content">