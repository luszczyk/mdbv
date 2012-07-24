<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type"
	content="application/xhtml+xml; charset=UTF-8" />
<meta name="author" content="Lukasz Luszczynski - www.luszczyk.net" />
<meta name="robots" content="index, follow" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/web/resources/css/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/web/resources/css/pixDisplay.css" />
<script type="text/javascript" src="/web/resources/js/jquery.min.js" ></script>
<script type="text/javascript" src="/web/resources/js/pixDisplay.js" ></script>
<title>Multimedia Database Viewer</title>
</head>
<body>
	<div class="wrap background">
		<div id="search">
			<form action="/web/query/run" method="post">
				<fieldset>
					<input type="text" name="query" class="searchField"
						value="Search Keywords" /> <input type="submit" class="button"
						value="" />
				</fieldset>
			</form>
		</div>
		<ul id="menu">
			<li><a class="current" href="/web/index">Home</a></li>
			<li><a href="#">Settings</a></li>
		</ul>

		<div class="clear"></div>

		<div id="content">
			<div class="resultBox">

				<h3>Result</h3>

				<table id="gradient-style">
					<thead>
						<tr>
							<c:forEach var="c" items="${tabele.columns}">
								<th scope="col">${c.name} ${c.type}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="e" items="${tabele.entities}">
							<tr>
								<c:forEach var="o" items="${e.values}">
									<td><c:if test="${o.viewable}">
											<a rel="pixDisplay" href="domain/${o.value}/fileContent">view</a>
											<%-- <form action="domain.htm" method="post">
												<input type="hidden" name="path" value="${o.value}" />
												<input type="submit" name="view" />
											</form> --%>
										</c:if> <c:if test="${!o.viewable}">
									${o.type} ${o.value}
								</c:if></td>
								</c:forEach>
							</tr>
						</c:forEach>


					</tbody>

				</table>





				<%-- <table>
			<tr>
				<span class="item"> <c:forEach var="c"
						items="${tabele.columns}">
						<td>${c.name}</td>
					</c:forEach>
				</span>
			</tr>
			<tr>
				<span class="item"> <c:forEach var="c"
						items="${tabele.columns}">
						<td>${c.type}</td>
					</c:forEach>
				</span>
			</tr>

			<c:forEach var="e" items="${tabele.entities}">
				<tr>
					<span class="item"> <c:forEach var="o" items="${e.values}">
							<c:if test="${o.viewable}">
								<td><a href="domain/${o.value}/fileContent.htm">${o.type}</a>

									<form action="domain.htm" method="post">
										<input type="hidden" name="path" value="${o.value}" />
										<br> <input type="submit" name="view" />
									</form></td>
							</c:if>
							<c:if test="${!o.viewable}">
								<td>${o.type} ${o.value}</td>
							</c:if>
						</c:forEach>
					</span>
					<tr>
			</c:forEach>


		</table> --%>

			</div>
		</div>
		<div class="boxbottom"></div>

		<div id="promo">
			<div class="wrap">
				<div class="col"></div>
				<div class="col">
					<h2>Description</h2>
					<p></p>
				</div>
			</div>
			<div id="footer">
				<p>
					Copyright &copy; 2012 &minus; Multimedia Database Viewer &minus;
					Design: Lukasz Luszczynski , <a href="http://luszczyk.net">luszczyk</a>
				</p>
			</div>
		</div>
</body>
</html>