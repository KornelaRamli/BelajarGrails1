<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14/06/2017
  Time: 15:13
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Peta</title>
    %{--<asset:javascript src="leaflet-src.js"></asset:javascript>--}%
    <asset:javascript src="leaflet.js"></asset:javascript>
    <asset:stylesheet src="leaflet.css"></asset:stylesheet>

    %{--<asset:javascript src="leaflet.markercluster-src.js"></asset:javascript>--}%
    %{--<asset:javascript src="leaflet.markercluster.js"></asset:javascript>--}%
    %{--<asset:stylesheet src="MarkerCluster.css"></asset:stylesheet>--}%
    %{--<asset:stylesheet src="MarkerCluster.Default.css"></asset:stylesheet>--}%

    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/leaflet.markercluster/0.4.0/MarkerCluster.css"/>
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/leaflet.markercluster/0.4.0/MarkerCluster.Default.css"/>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/leaflet.markercluster/0.4.0/leaflet.markercluster.js">    </script>
    %{--<script src="file:///D:\Desktop\Leaflet.markercluster-leaflet-0.7\dist\leaflet.markercluster-src.js">    </script>--}%

</head>

<body>
    <div id="peta"></div>
    <div id="fullmap" class="row">
        <div class="leaflet-top leaflet-right">
            hai
        </div>
    </div>

    <script>
        var result = ${raw((result as grails.converters.JSON) as String)};
    </script>

    <asset:javascript src="peta.js"></asset:javascript>
</body>
</html>