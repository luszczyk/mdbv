<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/template/header.jsp" %>

<h3>Row Details</h3>

<div class="resultBox">

    <div class="results">
        <c:forEach var="o" items="${e.values}">
            <div class="field-details">

                <table style="padding-left: 14px;">
                    <tr class="p75">
                        <td class="field-label">
                            Type:
                        </td>
                        <td class="field-val">
                                ${o.column.type}
                        </td>
                    </tr>
                    <tr class="p75">
                        <td class="field-label">
                            Name:
                        </td>
                        <td class="field-val">
                                ${o.column.name}
                        </td>
                    </tr>
                    <tr class="p75">
                        <td class="field-label">Value:</td>
                        <td class="field-val">
                            <c:if test="${o.viewable}">
                                ${o.linkToView}
                            </c:if>
                            <c:if test="${!o.viewable}">
                                ${o.preView}
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </div>

</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp" %>