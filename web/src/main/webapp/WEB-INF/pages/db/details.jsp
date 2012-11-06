<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/template/header.jsp" %>
<div class="db_list_box">

    <script type="text/javascript">


        function clearSchemaList() {
            $('.schemaElements').css('display', 'none');
        };

        function clearTableList() {
            $('.tableElements').css('display', 'none');
        };

        function showAllSchemas(dbName) {
            clearTableList();
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
                            tableElement.append('<li><a href="#" onclick="showAllTables(\''+this +'\')">' + this + ' >></a></li>')
                        });
                        $('.schemaElements').css('display', 'block');
                    } else {
                        $().toastmessage('showErrorToast', res.msg);
                    }
                },
                error:function (e) {
                    $().toastmessage('showErrorToast', e);
                }
            });
        };

        function showAllTables(schema) {
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
                        $.each(res.data, function () {
                            tableElement.append('<li><a href="#">' + this + '</a></li>')
                        });
                        $('.tableElements').css('display', 'block');
                    } else {
                        $().toastmessage('showErrorToast', res.msg);
                    }
                },
                error:function (e) {
                    $().toastmessage('showErrorToast', e);
                }
            });
        };

    </script>

    <table>

        <tr>

            <td><h3>Databases :</h3></td>
            <td><h3 class="schemaElements" style="display: none;">Schemas :</h3></td>
            <td><h3 class="tableElements" style="display: none;">Tables :</h3></td>
        </tr>

        <tr>
            <td>
                <div class=db-list style="padding: 15px;">
                    <ul>
                        <c:forEach items="${dbs}" var="d">
                            <li>
                                <div class="db-details">
                                    <a href="#" onclick="return showAllSchemas('${d}');">${d} >> </a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </td>
            <td>
                <div style="display: none;" class="db-schema-panel schemaElements">
                    <ul id="db-schemas">

                    </ul>
                </div>
            </td>
            <td>
                <div style="display: none;" class="db-tables-panel tableElements">
                    <ul id="db-tables">

                    </ul>
                </div>
            </td>
        </tr>

    </table>
</div>
<%@ include file="/WEB-INF/pages/template/buttom.jsp" %>