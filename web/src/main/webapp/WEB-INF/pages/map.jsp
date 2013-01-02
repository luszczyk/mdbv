<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/template/head.jsp"%>

<script type="text/javascript">


    function init() {
        OpenLayers.ImgPath = "/web/resources/js/img/";
        OpenLayers.LibPath = "/web/resources/js/lib/";
        var baseLayer = new OpenLayers.Layer("Blank",{isBaseLayer: true});

        var map = new OpenLayers.Map({
            div: "map",
            maxExtent: new OpenLayers.Bounds(-7000000, -7000000, 7000000, 7000000),
            layers: [
                baseLayer
            ]
        });

        var wkt = new OpenLayers.Format.WKT();


        var vectors = new OpenLayers.Layer.Vector('My Vectors');
        map.addLayer(vectors);


        var wkt = new OpenLayers.Format.WKT();

        var polygonFeature = wkt.read("<%=session.getAttribute("res")%>");
        polygonFeature.geometry.transform(map.displayProjection, map.getProjectionObject());
        vectors.addFeatures([polygonFeature]);

        map.zoomToExtent(vectors.getDataExtent());

    };
</script>


</head>
<body onload="init();">

<div id="map" class="smallmap"></div>

</body>
</html>
