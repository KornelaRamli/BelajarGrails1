<html>
<head>
    <meta name="layout" content="main">
    <title>CITY</title>
    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css"/>
    <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>

</head>

<body>
<div id="divShow">

</div>
    <div class="row">
        <br/>
        <g:if test="${result?.message}" >
            <p class="text text-danger bg-warning h3 ">${result.message}</p>
        </g:if>
    </div>
    <div class="row text-primary">
        <h1>
            ${city.nama}
        </h1>
    </div>
    <div class="row">
        <table>
            <tr>
                <td>Provinsi</td>
                <td>${city.provinsi.nama}</td>
            </tr>
            <tr>
                <td>Deskripsi</td>
                <td> ${city.deskripsi}</td>
            </tr>
            <tr>
                <td>Daftar Kecamatan</td>
                <td>
                    <g:each var="dis" in="${district}">
                        %{--<g:link controller="district" action="showDetail" params="[id:${dis.id}]">--}%
                            ${dis.nama} : ${dis.id}<br/>
                        %{--</g:link>--}%

                    </g:each>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    Posisi
                    <g:hiddenField id="txtLatlng" name="txtLatlng" value="${city.latlng}"/>
                </td>

            </tr>
            <tr>
                <td id="map" colspan="2"></td>
            </tr>
        </table>
    </div>
    <div class="row">
        <g:link params="${city}" action="update" class="btn btn-success">Edit</g:link>
        <g:link params="${city}" action="delete" class="btn btn-danger" onclick="return confirm('${message(code:'default.button.delete.confirm.message',default:'Apakah anda yakin menghapus?')}')">Delete</g:link>
    </div>

</body>
</html>