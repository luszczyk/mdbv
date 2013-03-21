<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/template/header.jsp" %>

<h2>Row Details</h2>

<div class="resultBox">

    <div class="results">
        <c:forEach var="o" items="${e.values}">
            <div class="field-details">

                <div style="float: left;">
                    <div class="field-desc">
                        <div class="desc-panel">
                            <div class="field-label">Type</div>
                            <div class="field-val">${o.column.type}</div>
                        </div>
                        <div class="desc-panel">
                            <div class="field-label">Name</div>
                            <div class="field-val">${o.column.name}</div>
                        </div>
                    </div>
                    <c:if test="${!o.viewable}">
                        <div class="field-standard-value">
                            <textarea rows="1" cols="1" disabled="disabled">${o.preView}</textarea>
                        </div>
                    </c:if>
                    <c:if test="${o.viewable}">
                        <div class="field-standard-value">
                            <textarea rows="1" cols="1" disabled="disabled">${o.startBytes}</textarea>
                        </div>
                    </c:if>
                </div>

                <c:if test="${o.viewable}">
                    <div style="float: left;">
                        <div class="field-multimedia-panel">
                            <div class="desc-panel">
                                <div class="field-label">
                                    Multimedia Type:
                                </div>
                                <div class="field-val">
                                        ${o.mimeType}
                                </div>
                            </div>
                            <div class="field-multimedia-value">
                                <div style="float: left; margin-left: 60px; padding-top: 10px;">${o.linkToView}</div>
                                    ${o.icon}
                            </div>

                        </div>
                    </div>
                </c:if>

                <div class="clear"></div>
            </div>
        </c:forEach>
    </div>

</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp" %>