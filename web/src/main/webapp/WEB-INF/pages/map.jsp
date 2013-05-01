<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/template/head.jsp" %>

<script type="text/javascript">


    var map_request_in_process = false;

    var mapInit = function() {

        if (!map_request_in_process) {

            map_request_in_process = true;
            $("#spinner").show();

            OpenLayers.ImgPath = "/web/resources/js/img/";
            OpenLayers.LibPath = "/web/resources/js/lib/";

            var lat = 47.35387;
            var lon = 8.43609;
            var zoom = 18;

            console.log("mapInit 1");

            var fromProjection = new OpenLayers.Projection("EPSG:4326");   // Transform from WGS 1984
            var toProjection = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
            var position = new OpenLayers.LonLat(lon, lat).transform(fromProjection, toProjection);

            console.log("mapInit 2");

            var map = new OpenLayers.Map({
                div: "map"
            });

            map.addLayer(new OpenLayers.Layer.OSM());

            console.log("mapInit 3");
            var vectors = new OpenLayers.Layer.Vector();
            console.log("mapInit 4");

            var wkt = new OpenLayers.Format.WKT();

            var polygonFeature = wkt.read('<%=session.getAttribute("res")%>');
            polygonFeature.geometry.transform(map.displayProjection, map.getProjectionObject());
            vectors.addFeatures([polygonFeature]);
            console.log("mapInit 4.1");


            console.log("mapInit 5.1");
            map.addLayer(vectors);

            console.log("mapInit 6");
            map.zoomToExtent(vectors.getDataExtent());
            console.log("mapInit 6.1");

            $("#spinner").hide();
            map_request_in_process = false;
        }
    };
</script>
</head>
<body onload="mapInit()">

<div id="map" style="width: 100%; height: 100%"></div>
</body>
</html>
