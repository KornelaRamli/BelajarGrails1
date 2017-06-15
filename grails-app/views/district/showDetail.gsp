<html>
<head>
    <meta name="layout" content="main">
    <title>District</title>
    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css"/>
    <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
</head>

<body>
<div id="divShow"></div>
    <div class="row">
        <br/>
        <g:if test="${result?.message}" >
            <p class="text text-danger bg-warning h3 ">${result.message}</p>
        </g:if>
    </div>
    <div class="row text-primary">
        <h1>
            ${district.nama}
        </h1>
    </div>
    <div class="row">
        <table>
            <tr>
                <td>Kota</td>
                <td>${district.city.nama}</td>
            </tr>
            <tr>
                <td>Deskripsi</td>
                <td> ${district.deskripsi}</td>
            </tr>
            <tr>
                <td>Daftar Kelurahan</td>
                <td>
                    <g:each var="vil" in="${village}">
                        <g:link controller="village" action="showDetail" params="${vil.id}">
                            ${vil.nama}<br/>
                        </g:link>

                    </g:each>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    Posisi
                    <g:hiddenField id="txtLatlng" name="txtLatlng" value="${district.latlng}"/>
                </td>

            </tr>
            <tr>
                <td id="map" colspan="2"></td>
            </tr>
        </table>
    </div>
    <div class="row">
        <g:link params="${district}" action="update" class="btn btn-success">Edit</g:link>
        <g:link params="${district}" action="delete" class="btn btn-danger" onclick="return confirm('${message(code:'default.button.delete.confirm.message',default:'Apakah anda yakin menghapus?')}')">Delete</g:link>
    </div>
</body>
</html>