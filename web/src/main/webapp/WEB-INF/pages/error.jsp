<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<div class="errorBox">

	<c:forEach items="${e.stackTrace}" var="element">
		<c:out value="${element}" />
	</c:forEach>

</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>