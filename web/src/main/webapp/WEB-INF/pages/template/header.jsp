<%@ page import="net.luszczyk.mdbv.common.dto.DataBaseDTO" %>
<%@ include file="/WEB-INF/pages/template/head.jsp"%>
</head>
<body>
<div class="wrap background">
    <div class="spinner" id="spinner"></div>
    <div id="db-info">

        <%
            if (session.getAttribute("db") != null) {
        %>
        <div class="db_details">
            <div>Host: <span><c:out
                    value='<%=((DataBaseDTO) session.getAttribute("db")).getHost()%>'/></span></div>
            <div>Database: <span><c:out
                    value='<%=((DataBaseDTO) session.getAttribute("db")).getName()%>'/></span></div>
            <div>User: <span><c:out
                    value='<%=((DataBaseDTO) session.getAttribute("db")).getUser()%>'/></span></div>
        </div>
        <%
        } else {
        %>
        <div class="db_details">
            <div>No connection database</div>
        </div>
        <% } %>
    </div>
    <ul id="menu">
        <li><a class="menu_link" href="/web/query/index">Query</a></li>
        <li><a class="menu_link" href="/web/index">DB Connection</a></li>
        <li><a class="menu_link" href="/web/db/details">DB Details</a></li>
    </ul>

    <div class="clear"></div>

    <div id="content" class="content">