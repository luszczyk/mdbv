<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="net.luszczyk.mdbv.common.model.DataBase" %>
<%@ page session="true" %>
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
	$(function() {
		$("input:submit, a, button", ".but").button();
		$("a", ".but").click(function() {
			return false;
		});
	});

	function connTest() {
		if ($('#host').val == "" && $('#user').val == ""
				&& $('#port').val == "" && $('#pass').val == "") {
			$()
					.toastmessage('showNoticeToast',
							'Fill all fields in formular !');
		} else {
			var dataBase = new Object();
			dataBase.host = $('#host').val();
			dataBase.user = $('#user').val();
			dataBase.port = $('#port').val();
			dataBase.pass = $('#pass').val();
			dataBase.name = $('#name').val();

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
					} else {
						$().toastmessage('showErrorToast', res.msg);
					}
				},
				error : function(e) {
					$().toastmessage('showErrorToast', e);
				}
			});
		}
	}

	function dbConnect() {
		if ($('#host').val == "" && $('#user').val == ""
				&& $('#port').val == "" && $('#pass').val == "") {
			$()
					.toastmessage('showNoticeToast',
							'Fill all fields in formular !');
		} else {
			var dataBase = new Object();
			dataBase.host = $('#host').val();
			dataBase.user = $('#user').val();
			dataBase.port = $('#port').val();
			dataBase.pass = $('#pass').val();
			dataBase.name = $('#name').val();

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
	}
</script>
<title>Multimedia Database Viewer - ${h.title}</title>
</head>
<body>
	<div class="wrap background">
		<div id="db-info">

				<c:if test='<%=request.getSession().getAttribute("db") != null%>'> 
				<c:out value='<%=((DataBase) request.getSession().getAttribute("db")).getHost() %>'/>
				<span>Host: <c:out value='<%=((DataBase) request.getSession().getAttribute("db")).getHost() %>'/></span>
				<span>Database: <c:out value='<%=((DataBase) request.getSession().getAttribute("db")).getDbName() %>'/></span>
				<span>User: <c:out value='<%=((DataBase) request.getSession().getAttribute("db")).getUser() %>'/></span>
				</c:if>
		</div>
		<ul id="menu">
			<li><a class="current" href="/web/index">Home</a></li>
			<li><a href="#">Settings</a></li>
		</ul>

		<div class="clear"></div>

		<div id="content">