<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div class="resultBox">

	<h2>${h.title}</h2>

	<table id="gradient-style">
		<thead>
			<tr>
				<c:forEach var="d" items="${e.values}">
					<th scope="col"><strong>${d.column.name}</strong>
						[${d.column.type}]</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="o" items="${e.values}">
					<td><c:if test="${o.viewable}">
											${o.linkToView}
										</c:if> <c:if test="${!o.viewable}">
									${o.preView}
								</c:if></td>
				</c:forEach>
			</tr>
		</tbody>

	</table>
</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>