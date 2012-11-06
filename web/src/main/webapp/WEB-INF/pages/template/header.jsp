<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
	import="net.luszczyk.mdbv.common.model.DataBase"%>
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
	href="/web/resources/css/jquery.toastmessage.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/web/resources/css/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
          href="/web/resources/css/prettyPhoto.css" />
<script type="text/javascript" src="/web/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/web/resources/js/jquery.prettyPhoto.js"></script>
<script type="text/javascript"
	src="/web/resources/js/jquery.toastmessage.js"></script>
<script type="text/javascript"
	src="/web/resources/js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript">

	function connTest(dbform) {
		if ($('#dbhost').val() == "" && $('#dbuser').val() == ""
				&& $('#dbport').val() == "" && $('#dbpass').val() == "") {
			$()
					.toastmessage('showNoticeToast',
							'Fill all fields in formular !');
		} else {
			var dataBase = new Object();
			dataBase.host = $('#dbhost').val();
			dataBase.user = $('#dbuser').val();
			dataBase.port = $('#dbport').val();
			dataBase.pass = $('#dbpass').val();
			dataBase.name = $('#dbname').val();

			$.ajax({
				url : "/web/db/test.json",
				type : "POST",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				data : JSON.stringify(dataBase),
				success : function(res) {
					if (res.status == 0) {
						$().toastmessage('showSuccessToast', res.msg);
						dbform.submit();
						return true;
					} else {
						$().toastmessage('showErrorToast', res.msg);
						return false;
					}
				},
				error : function(e) {
					$().toastmessage('showErrorToast', e);
					return false;
				}
			});
		}
	};

	function dbConnect(dbform) {
		if ($('#dbhost').val == "" && $('#dbuser').val == ""
				&& $('#dbname').val == "" && $('#dbport').val == ""
				&& $('#dbpass').val == "") {
			$()
					.toastmessage('showNoticeToast',
							'Fill all fields in formular !');
		} else {
			var dataBase = new Object();
			dataBase.host = $('#dbhost').val();
			dataBase.user = $('#dbuser').val();
			dataBase.port = $('#dbport').val();
			dataBase.pass = $('#dbpass').val();
			dataBase.name = $('#dbname').val();

			$.ajax({
				url : "/web/db/connect.json",
				type : "POST",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				data : JSON.stringify(dataBase),
				success : function(res) {
					if (res.status == 0) {
						$().toastmessage('showSuccessToast', res.msg);
						window.location.replace("/web/query/index");
					} else {
						$().toastmessage('showErrorToast', res.msg);
					}
				},
				error : function(e) {
					$().toastmessage('showErrorToast', e);
				}
			});
		}
	};

</script>
<title>Multimedia Database Viewer - ${h.title}</title>
</head>
<body>
	<div class="wrap background">
		<div id="db-info">

			<%
				if (session.getAttribute("db") != null) {
			%>
			<div class="db_details">
				<div>Host: <span><c:out
						value='<%=((DataBase) session.getAttribute("db")).getHost()%>' /></span></div>
				<div>Database: <span><c:out
						value='<%=((DataBase) session.getAttribute("db")).getDbName()%>' /></span></div>
				<div>User: <span><c:out
						value='<%=((DataBase) session.getAttribute("db")).getUser()%>' /></span></div>
			</div>
			<%
				} else {
			%>
			<div class="db_details">
				<div>No connection database</div>
			</div>
			<% } %>
		</div>
		<ul id="menu">
			<li><a class="menu_link" href="/web/query/index">Query</a></li>
			<li><a class="menu_link" href="/web/index">DB Connection</a></li>
            <li><a class="menu_link" href="/web/db/details">DB Details</a></li>
		</ul>

		<div class="clear"></div>

		<div id="content">