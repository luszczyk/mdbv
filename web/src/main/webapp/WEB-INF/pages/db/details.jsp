<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/template/header.jsp" %>
<div class="db_list_box">

    <script type="text/javascript">


        function clearSchemaList() {
            $('.schemaElements').css('display', 'none');
        }
        ;

        function clearTableList() {
            $('.tableElements').css('display', 'none');
        }
        ;

        function markLink(link, context) {
            var cl = 'active-link-' + context;
            $('.' + cl).each(function (i, l) {
                $(l).removeClass(cl);
            });
            $(link).parent().addClass(cl);
        }
        ;

        function showAllSchemas(link, dbName) {
            clearTableList();
            markLink(link, 'schema');

            $.ajax({
                url:"/web/db/details/all-schemas/" + dbName,
                type:"POST",
                headers:{
                    'Accept':'application/json',
                    'Content-Type':'application/json'
                },
                success:function (res) {
                    if (res.status == 0) {
                        $().toastmessage('showSuccessToast', res.msg);
                        var tableElement = $('#db-schemas')
                        tableElement.empty()
                        $.each(res.data, function () {
                            tableElement.append('<li><a href="#" onclick="showAllTables(this, \'' + this + '\')">' + this + '</a></li>')
                            $(tableElement.parent()).addClass('vertical-line');
                        });
                        $('.schemaElements').css('display', 'block');
                        $('#action-desc').text("Select schema");
                    } else {
                        $().toastmessage('showErrorToast', res.msg);
                    }
                },
                error:function (e) {
                    $().toastmessage('showErrorToast', e);
                }
            });
        }
        ;

        function showAllTables(link, schema) {
            markLink(link, 'table');
            $.ajax({
                url:"/web/db/details/all-tables/" + schema,
                type:"POST",
                headers:{
                    'Accept':'application/json',
                    'Content-Type':'application/json'
                },
                success:function (res) {
                    if (res.status == 0) {
                        var tableElement = $('#db-tables')
                        tableElement.empty()
                        $.each(res.data.table, function () {
                            if (res.data.schema) {
                                tableElement.append('<li><a href="/web/query/index/' + res.data.schema + '/' + this + '">' + this + '</a></li>')
                            } else {
                                tableElement.append('<li><a href="/web/query/index/' + this + '">' + this + '</a></li>')
                            }
                            $(tableElement.parent()).addClass('vertical-line');
                        });
                        $('.tableElements').css('display', 'block');
                        $('#action-desc').text("Select table");
                    } else {
                        $().toastmessage('showErrorToast', res.msg);
                    }
                },
                error:function (e) {
                    $().toastmessage('showErrorToast', e);
                }
            });
        }
        ;

    </script>


    <h2 id="action-desc">Select database</h2>

    <div class="col-wraper">
        <div class="db-list inline" style="padding-left: 15px; padding-right: 5px; min-width: 200px;">
            <ul>
                <c:forEach items="${dbs}" var="d">
                    <li>
                        <div class="db-details">
                            <c:if test="${dbHasSchemas}">
                                <a href="#" onclick="return showAllSchemas(this, '${d}');">${d} </a>
                            </c:if>
                            <c:if test="${!dbHasSchemas}">
                                <a href="#" onclick="return showAllTables(this, '${d}');">${d} </a>
                            </c:if>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <c:if test="${dbHasSchemas}">
            <div style="display: none; min-width: 200px;" class="db-schema-panel schemaElements inline">
                <ul id="db-schemas" class="db-schemas">

                </ul>
            </div>
        </c:if>
        <div style="display: none; min-width: 200px;" class="db-tables-panel tableElements inline">
            <ul id="db-tables" class="db-tables">

            </ul>
        </div>
        <div class="clearBoth"></div>
    </div>

</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp" %>