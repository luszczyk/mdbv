<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/template/header.jsp" %>

<h3>Row Details</h3>

<div class="resultBox">

    <div class="results">
        <c:forEach var="o" items="${e.values}">
            <div class="field-details">

                <div class="field-desc">
                    <div class="desc-panel">
                        <div class="field-label">Database Type:</div>
                        <div class="field-val">${o.column.type}</div>
                    </div>
                    <div class="desc-panel">
                        <div class="field-label">Database Column Name:</div>
                        <div class="field-val">${o.column.name}</div>
                    </div>
                </div>

                <c:if test="${o.viewable}">
                    <div class="field-multimedia-panel">
                        <div class="desc-panel">
                            <div class="field-label">
                                Multimedia Type:
                            </div>
                            <div class="field-val">
                                    ${o.mimeType}
                            </div>
                        </div>
                        <div class="field-multimedia-value"><div class="wrap">${o.linkToView}</div></div>
                    </div>
                </c:if>


                <c:if test="${!o.viewable}">
                    <div class="field-standard-value">
                        <textarea rows="1" cols="1">${o.preView}</textarea>
                    </div>
                </c:if>

                <div class="clear"></div>
            </div>
        </c:forEach>
    </div>

</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp" %>