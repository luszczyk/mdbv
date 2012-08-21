<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div class="formBox">
	<br>
	<div class="formContainer">
		<form action="db/connect" method="post" id="form2">
			<h3>Type data base credentials</h3>
			<fieldset>
				<p class="first">
					<label>Host:</label> <br> <input type="text" id="host"
						name="db.host" class="field" value="host" />
				</p>
				<p>
					<label>Port:</label> <input type="text" id="port" name="db.port"
						class="field" value="port" />
				</p>
				<p>
					<label>Database:</label> <input type="text" id="name" name="db.name"
						class="field" value="database" />
				</p>
				<p>
					<label>User:</label> <input type="text" id="user" name="db.user"
						class="field" value="user" />
				</p>
				<p class="last">
					<label>Pass:</label> <input type="password" id="pass" name="db.pass"
						class="field" value="password" />
				</p>
				<br>
				<div class="submit">
				<div class='but' style="padding: 2px; display: inline; width: 250px;">
					<a href="#" onclick="connTest();">Test</a>
				</div>
				<div class='but' style="padding: 2px; display: inline; width: 250px;">
					<input type="submit" id="submit" name="Connect" value="Connect"/>
				</div>

				</div>
			</fieldset>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>