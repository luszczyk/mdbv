<%@ page import="net.luszczyk.mdbv.common.dto.DataBaseDTO" %>
<%@ include file="/WEB-INF/pages/template/head.jsp"%>
</head>
<body class="main">
<div class="wrap background">
    <div class="spinner" id="spinner"></div>
    <div id="db-info">

        <%
            if (session.getAttribute("db") != null) {
        %>
        <div class="db_details">

            <div style="float: right;">
                <ul class="main-menu">
                    <li><a class="menu_link" onclick="dbDisconnect();">Disconnect</a></li>
                </ul>
            </div>
            <div><span class="label">Host</span><span class="info"><c:out
                    value='<%=((DataBaseDTO) session.getAttribute("db")).getHost()%>'/></span></div>
            <div><span class="label">User</span><span class="info"><c:out
                    value='<%=((DataBaseDTO) session.getAttribute("db")).getUser()%>'/></span></div>
            <div><span class="label">Type</span><span class="info"><%=((DataBaseDTO) session.getAttribute("db")).getType()%></span></div>
            <br>
            <div><span class="label">Name</span><span class="info"><c:out
                    value='<%=((DataBaseDTO) session.getAttribute("db")).getName()%>'/></span></div>
        </div>
        <%
        } else {
        %>
        <div class="db_details">
            <div>No connection database</div>
        </div>
        <% } %>
    </div>
    <ul class="main-menu">
        <li><a class="menu_link" href="/web/query/index">Query</a></li>
        <li><a class="menu_link" href="/web/index">Connection</a></li>
        <li><a class="menu_link" href="/web/db/details">DB Details</a></li>
        <li><a class="menu_link" href="/web/about">About</a></li>
    </ul>

    <div class="clear"></div>

    <div id="content" class="content">