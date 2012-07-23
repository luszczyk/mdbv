<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type"
	content="application/xhtml+xml; charset=UTF-8" />
<meta name="author" content="Łukasz Łuszczyński - www.luszczyk.net" />
<meta name="robots" content="index, follow" />
<link rel="stylesheet" type="text/css" media="screen"
	href="/css/style.css" />
<title>Multimedia Database Viewer</title>
</head>
<body>
	<div class="wrap background">
		<div id="search">
			<form action="run.htm" method="post">
				<fieldset>
					<input type="text" name="query" class="field"
						value="Search Keywords" /> <input type="submit" class="button"
						value="" />
				</fieldset>
			</form>
		</div>
		<ul id="menu">
			<li><a class="current" href="#">HOME</a></li>
			<li><a href="#">ARTS</a></li>
			<li><a href="#">BIOGRAPHY</a></li>
			<li><a href="#">GEOGRAPHY</a></li>
			<li><a href="#">HISTORY</a></li>
			<li><a href="#">MATHEMATICS</a></li>
			<li><a href="#">SCIENCE</a></li>
		</ul>

		<div id="logo">
			<h1>
				<a href="#">Internet<br />Encyclopedia
				</a>
			</h1>
			<h2 id="slogan">Hunger for Knowledge</h2>
		</div>

		<ul id="feature_menu">
			<li><a class="current" href="#">DID YOU KNOW...</a></li>
			<li><a href="#">ON THIS DAY</a></li>
			<li><a href="#">FEATURED CONTENT</a></li>
		</ul>

		<div id="feature">
			<img src="images/feature_img.gif" alt="Featured" />
			<p>Mauris magna sem, pellentesque sit amet, nonummy vel, nonummy
				id, velit. Mauris facilisis, quam ut semper adipiscing, magna diam
				laoreet ante, ac varius massa dolor sit amet augue.</p>
			<p>
				<a class="more" href="#">&not; READ MORE</a>
			</p>
		</div>

		<div class="clear"></div>

		<div id="left">
			<h2>
				<a href="#">Today's featured article</a>
			</h2>
			<p class="date">
				<span>18th</span><br />July
			</p>
			<p class="subtitle">Aenean justo nisl, luctus sit amet, malesuada
				ac, dignissim ac, eros</p>
			<p>
				Maecenas libero neque, volutpat sit amet, varius et, <em>pretium
					quis</em>, purus. Nulla ut magna. Nunc nec dui eget erat vulputate
				sagittis. Suspendisse fermentum odio. Mauris magna sem, pellentesque
				sit amet, nonummy vel, nonummy id, velit. <a href="#">Mauris
					facilisis quam ut semper adipiscing</a>, magna diam laoreet ante, ac
				varius massa dolor sit amet augue. Vivamus purus. Integer consequat.
				Nunc et nunc. <a href="#">Phasellus augue</a> diam, vestibulum non,
				iaculis eget, tristique sed, lectus. Sed pede. Nullam egestas ante a
				mauris. Aliquam metus turpis, luctus ac, sagittis eget, elementum
				tincidunt, massa. Aenean justo nisl, luctus sit amet, malesuada ac,
				dignissim ac, eros nec dui eget erat vulputate sagittis.
			</p>
			<p>
				<a class="more" href="#">&not; READ MORE</a> <a class="more"
					href="#">&not; COMMENT [12]</a> <a class="more" href="#">&not;
					SHARE</a>
			</p>
		</div>

		<div id="side">
			<div class="boxtop"></div>
			<div class="box">




				<table>
					<tr>
						<c:forEach var="c" items="${tabele.columns}">
							<td>${c.name}</td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach var="c" items="${tabele.columns}">
							<td>${c.type}</td>
						</c:forEach>
					</tr>

					<c:forEach var="e" items="${tabele.entities}">
						<tr>
							<c:forEach var="o" items="${e.values}">
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
							<tr>
					</c:forEach>


				</table>





				<h3>POPULAR ARTICLES</h3>
				<a href="#"> <span class="item"> <span class="sidedate">4.5<br />
						<span>RATING</span></span> <strong>Lorem ipsum dolor sit amet</strong><br />Consectetuer
						adipiscing elit.
				</span>
				</a> <a href="#"> <span class="item"> <span class="sidedate">4.5<br />
						<span>RATING</span></span> <strong>Suspendisse odio orci</strong><br />Nam
						tortor libero, dictum vulputate.
				</span>
				</a> <a href="#"> <span class="item last"> <span
						class="sidedate">4.5<br />
						<span>RATING</span></span> <strong>Aliquam metus turpis luctus
							ac</strong><br />Suspendisse egestas fringilla odio.
				</span>
				</a>
			</div>
			<div class="boxbottom"></div>
		</div>
		<p id="ad">Suspendisse egestas fringilla odio. Donec lacinia
			tristique ante. Cum sociis natoque penatibus et magnis dis parturient
			montes, nascetur ridiculus mus. Etiam blandit ultricies nisl. Nullam
			dapibus, mauris id scelerisque feugiat, sapien augue porta ipsum, ut
			blandit tellus enim vel mauris. Praesent accumsan metus vel.</p>
	</div>

	<div id="promo">
		<div class="wrap">
			<div class="col">
				<img id="featured" src="images/featured.gif" alt="Featured Image" />
			</div>
			<div class="col">
				<h2>About Solucija</h2>
				<p>
					Solucija offers a selection of some of the hottest web templates
					available. Looking for a free web template, commercial one, or an
					inspiration? You'll find it on <a href="http://www.solucija.com"
						title="Free Templates">Solucija</a>.
				</p>
			</div>
			<div class="col last">
				<h2>Like this template?</h2>
				<p>
					If you liked this template, you might like some other Free CSS
					Templates from <a href="http://www.solucija.com"
						title="Information Architecture and Web Design">Solucija</a>.
				</p>
			</div>
			<div id="footer">
				<p>
					Copyright &copy; 2008 &minus; Internet Encyclopedia &minus; Design:
					Luka Cvrk, <a title="Awsome Web Templates"
						href="http://www.solucija.com/">Solucija</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>