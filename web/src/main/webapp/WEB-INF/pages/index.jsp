<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div class="formBox">
	<br>
	<h2>
		Type data base credentials
	</h2>
	<br>
	<form action="/web/db/index" method="post">
		<fieldset>
			<label>Host:</label>
			<input type="text" name="host" class="clear" value="host"/><br>
			<label>User:</label>
			<input type="text" name="user" class="clear" value="user"/><br>
			<label>Pass:</label>
			<input type="password" name="pass" class="clear" value="password"/><br>
			<button id="test" value="connect" onclick=""/> 
		</fieldset>
	</form>
</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>