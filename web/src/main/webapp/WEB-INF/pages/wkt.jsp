<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/template/head.jsp" %>

<script type="text/javascript">
    var wkt_request_in_process = false;

    var wktInit = function() {

        if (!wkt_request_in_process) {

            $("#spinner").show();
            wkt_request_in_process = true;
            OpenLayers.ImgPath = "/web/resources/js/img/";
            OpenLayers.LibPath = "/web/resources/js/lib/";
            var baseLayer = new OpenLayers.Layer("Blank", {isBaseLayer:true});

            var map = new OpenLayers.Map({
                div:"wkt",
                maxExtent:new OpenLayers.Bounds(-7000000, -7000000, 7000000, 7000000),
                layers:[
                    baseLayer
                ]
            });

            var vectors = new OpenLayers.Layer.Vector('My Vectors');
            map.addLayer(vectors);

            var wkt = new OpenLayers.Format.WKT();

            var polygonFeature = wkt.read('<%=session.getAttribute("res")%>');
            polygonFeature.geometry.transform(map.displayProjection, map.getProjectionObject());
            vectors.addFeatures([polygonFeature]);

            map.zoomToExtent(vectors.getDataExtent());
            wkt_request_in_process = false;
            $("#spinner").hide();
        } else {
            console.log("Request processing ...")
        }

    };
</script>
</head>
<body onload="wktInit()">

    <div id="wkt" style="width: 100%; height: 100%"></div>
</body>
</html>








