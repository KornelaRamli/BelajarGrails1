<html>
<head>
    <meta name="layout" content="main">
    <title>CITY</title>
    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css"/>
    <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js">    </script>

</head>

<body>
<div id="divCreate"></div>
    <div class ="row">
        <h1>Buat Kota Baru</h1>
    </div>
%{--table--}%
    <div class="row">
        <g:if test="${result?.message}">
            <span style="font-weight: bold;color: #cc0000">
                <g:message code="${result.message}" args="${result.args}"></g:message>
            </span>
        </g:if>
        <g:form action="create">
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
                        <div id="showTxt">Pilih pada peta</div>
                        <g:hiddenField id="txtPosisi" name="latlng" value="${city?.latlng}" />
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