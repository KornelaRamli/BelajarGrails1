<html>
<head>
    <meta name="layout"content="main">
    <title>Village Show</title>
</head>

<body id="show">

<div class="row">
    <g:if test="${result?.message}">
        <p class="text text-success bg-primary">${result.message}</p>
    </g:if>
</div>
<div class="row">
    <br/>
    <h2>Daftar Kelurahan</h2>
    <br/>
</div>
<div class="row">
    <div class="col-md-9">
        <table>
            <col width="200"/>
            <col width="200"/>
            <col width="450"/>
            <tr>
                <th>Kelurahan</th>
                <th>Kecamatan</th>
                <th>Deskripsi</th>

            </tr>
            <g:each var="v" in="${village}">
                <tr>
                    <td>
                        <g:link action="showDetail" params="${v}">${v.nama}</g:link>
                    </td>
                    <td>${v.district.nama}</td>
                    <td>${v.deskripsi}</td>
                </tr>
            </g:each>

        </table>
    </div>

    <div class="col-md-3">
        <g:form action="search">
            <div class="row">
                <h4>Search</h4>
            </div><br/>
            <div class="row">
                <p class="text color-palette">Nama</p>
                <g:textField name="nama"></g:textField>
            </div><br/>
            <div class="row"><br/>
            <g:actionSubmit value="Search" class="btn btn-success" />
        </g:form>
            <a href="/village/create" class="btn btn-primary">Tambah Kelurahan</a>
        </div>
    </div>

</div>



</body>
</html>