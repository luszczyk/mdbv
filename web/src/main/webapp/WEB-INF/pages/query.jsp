<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div id="query">
	<form action="/web/query/run" id="qform" method="post">
		<fieldset>
			<p class="first submit">
			<input type="text" name="query"
					class="searchField clear" id="searchBox"
					value="Enter SQL query here" />
				<button type="submit">Query</button>
			</p>
		</fieldset>
	</form>
</div>
<div class="resultBox">
	<br>
	<code>${select}</code>
	<br>
	<table id="gradient-style">
		<thead>
			<tr>
				<c:forEach var="c" items="${tabele.columns}">
					<th scope="col">${c.name} ${c.type}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="e" items="${tabele.entities}">

				<tr>
					<c:forEach var="o" items="${e.values}">
						<td><a href="/web/row/index/${e.id}">${o.preView}</a></td>
					</c:forEach>
				</tr>
			</c:forEach>


		</tbody>

	</table>
</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>