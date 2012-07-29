<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp" %>
<div class="resultBox">

	<h3>${h.title}</h3>

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
						<td><c:if test="${o.viewable}">
								<%-- <a rel="pixDisplay" href="domain/${o.value}/fileContent">view</a> --%>
											${o.link}
										</c:if> <c:if test="${!o.viewable}">
									${o.type} ${o.value}
								</c:if></td>
					</c:forEach>
				</tr>
			</c:forEach>


		</tbody>

	</table>
</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp" %>