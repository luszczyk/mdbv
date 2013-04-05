<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/template/head.jsp" %>

<script type="text/javascript">
/*
    var online = false;

    var loadMaps = function () {
        google.load("maps", "2", {"callback" : function () {
            online = true;
        }});
    }*/

    var init = function () {
/*
        if (online) {
            console.log("online");
        } else {
            console.log("offline");
        }*/

        mapInit();

        /*var type = navigator.network.connection.type;

         if (type != Connection.NONE) {
         console.log(type);
         mapInit();
         } else {
         alert("offline");
         }*/
    };

    var mapInit = function () {

                OpenLayers.ImgPath = "/web/resources/js/img/";
                OpenLayers.LibPath = "/web/resources/js/lib/";


                var lat = 47.35387;
                var lon = 8.43609;
                var zoom = 18;

                var fromProjection = new OpenLayers.Projection("EPSG:4326");   // Transform from WGS 1984
                var toProjection = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
                var position = new OpenLayers.LonLat(lon, lat).transform(fromProjection, toProjection);


                var map = new OpenLayers.Map({
                    div:"map"
                });

                map.addLayer(new OpenLayers.Layer.OSM());

                var vectors = new OpenLayers.Layer.Vector('My Vectors');
                map.addLayer(vectors);


                var wkt = new OpenLayers.Format.WKT();

                var polygonFeature = wkt.read("<%=session.getAttribute("res")%>");
                polygonFeature.geometry.transform(map.displayProjection, map.getProjectionObject());
                vectors.addFeatures([polygonFeature]);

                map.zoomToExtent(vectors.getDataExtent());

            }
            ;

</script>


</head>
<body onload="init();">

<div id="map" class="smallmap"></div>


</body>
</html>
