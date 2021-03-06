<html>
<head>
    <meta name="layout" content="main">
    <title>CITY</title>
    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css"/>
    <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>

</head>

<body>
<div id="divUpdate">

</div>

<div class ="row">
    <h1>Edit Kota ${city.nama}</h1>
</div>
<div class="row">

    <g:if test="${result?.message}">
        <span style="font-weight: bold;color: #cc0000">
            <g:message code="${result.message}" args="${result.args}"></g:message>
        </span>
    </g:if>
    <g:form action="update">
        <g:hiddenField name="id" value="${city?.id}"></g:hiddenField>
    %{--kolom nama kota dll--}%
        <div class="col-md-6">
            %{--baris per kota--}%
            <div class="row">
                <div class="col-md-6">
                    Nama Kota
                </div>
                <div class="col-md-6">
                    <g:textField name="nama" value="${city?.nama}"/>
                </div>
            </div>
            %{--baris provinsi--}%
            <div class="row">
                <div class="col-md-6">
                    Provinsi
                </div>
                <div class="col-md-6">
                    <g:select name="provinsi" from="${provincelist}" value="1" optionKey="id" optionValue="nama"/>
                </div>
            </div>
            %{--baris deskripsi--}%
            <div class="row">
                <div class="col-md-6">
                    Deskripsi
                </div>
                <div class="col-md-6">
                    <g:textArea name="deskripsi" value="${city?.deskripsi}"/>
                </div>
            </div>
        </div>
    %{--kolom peta dan lokasi--}%
        <div class="col-md-6">
            <div class="row">
                <div class="col-md-6">
                    Posisi
                </div>
                <div class="col-md-6">
                    <div id="showTxt">
                        <g:if test="${city.latlng}">
                            ${city?.latlng}

                        </g:if>
                        <g:else>
                            Pilih pada peta
                        </g:else>
                    </div>
                    <g:hiddenField id="txtLatlng" name="latlng" value="${city?.latlng}" />
                </div>
            </div>
            <div class="row" id="map">

            </div>
        </div>
        <g:submitButton class="btn btn-primary" name="submit">Buat</g:submitButton>


    </g:form>
</div>

</body>
</html>