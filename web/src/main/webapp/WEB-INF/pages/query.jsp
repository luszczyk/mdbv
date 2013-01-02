<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/header.jsp"%>
<script type="text/javascript">
    $(function(){
        $('#searchBox').autosize({append: "\n"});
    });

    function runQuery() {

        var queryMap = new Object();
        queryMap.query = $('#searchBox').val();
        $('#spinner').show();
        $.ajax({
            url:"/web/query/run.json",
            type:"POST",
            headers:{
                'Accept':'application/json',
                'Content-Type':'application/json'
            },
            data:JSON.stringify(queryMap),
            success:function (res) {
                $('#spinner').hide();
                if (res.status == 200) {
                    $().toastmessage('showToast',{
                        text:res.msg,
                        sticky:true,
                        stayTime:6000,
                        position:'top-right',
                        type:'success'
                    });
                    window.location.replace("/web/query/show/result");
                } else {
                    console.log(res);
                    $().toastmessage('showToast',{
                        text:res.msg,
                        stayTime:6000,
                        position:'top-right',
                        type:'error'
                    });
                }
            },
            error:function (e) {
                $('#spinner').hide();
                $().toastmessage('showToast',{
                    text:e,
                    stayTime:6000,
                    sticky:true,
                    position:'top-right',
                    type:'error'
                });
            }
        });
    }
</script>
<div id="query">
		<fieldset>
			<p class="first submit">
			<textarea name="query" style="height: 10px;" class="searchField clear" id="searchBox">${select}</textarea>
			<button type="button" onclick="runQuery();"  style="height: 34px;">Query</button>
			</p>
        </fieldset>
</div>
<div class="resultBox">
	<br />
	<table id="gradient-style">
		<thead>
			<tr>
				<c:forEach var="c" items="${table.columns}">
					<th scope="col">${c.name} ${c.type}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="e" items="${table.entities}">

				<tr>
					<c:forEach var="o" items="${e.values}">
						<td><a href="/web/row/index/${e.id}">${o.preView}</a></td>
					</c:forEach>
				</tr>
			</c:forEach>


		</tbody>

	</table>



</div>




<%@ include file="/WEB-INF/pages/template/buttom.jsp"%>