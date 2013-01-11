<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div class="formBox">
	<br>
	<div class="formContainer">
		<form action="db/connect" method="post" id="form2">
			<h3>Type data base credentials</h3>
			<fieldset>
                <p class="first">
                    <label>Type:</label>
                    <br>
                    <select id="dbtype" name="db.port">
                        <option value="MYSQL">Mysql</option>
                        <option value="POSTGRES">Postgres</option>
                    </select>
                </p>
				<p>
					<label>Host:</label> <br> <input type="text" id="dbhost"
						name="db.host" class="field" value="localhost" />
				</p>
				<p>
					<label>Port:</label> <input type="text" id="dbport" name="db.port"
						class="field" value="3307" />
				</p>
				<p>
					<label>Database:</label> <input type="text" id="dbname"
						name="db.name" class="field" value="mdbvdb" />
				</p>
				<p>
					<label>User:</label> <input type="text" id="dbuser" name="db.user"
						class="field" value="mdbv" />
				</p>
				<p class="last">
					<label>Pass:</label> <input type="password" id="dbpass"
						name="db.pass" class="field" value="mdbvdupa" />
				</p>
				<p class="submit">
					<button type="button" onclick="return dbConnect(this.form);">Connect</button>
				</p>
			</fieldset>
		</form>
	</div>
</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>