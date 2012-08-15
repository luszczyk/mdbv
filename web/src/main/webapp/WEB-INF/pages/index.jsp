<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div class="formBox">
	<br>
	<div id="side">
		<div class="boxtop"></div>
		<div class="box">
			<div class="formContainer">
			<h2>Type data base credentials</h2>
			<form action="#" method="post">
				<fieldset>
					<span><label>Host:</label></span> <input type="text" id="dbhost" name="host" class="clear"
						value="host" /> 
						
						<span><label>Port:</label></span>
					<input type="text" id ="dbport" name="port" class="clear" value="port" /><br>
						
						<span><label>User:</label></span> <input type="text"
						id="dbuser" name="user" class="clear" value="user" /> <span><label>Pass:</label></span>
					<input type="password" id ="dbpass" name="pass" class="clear" value="password" /><br>
					<br>
					<div class='but'>
						<a href="#" onclick="dbConnTest();">Connect</a>
					</div>
				</fieldset>
			</form>
			</div>
		</div>
		<div class="boxbottom"></div>
	</div>
</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>