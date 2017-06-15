<html>
<head>
    <meta name="layout"content="main">
    <title>District Show</title>
</head>

<body id="show">

<div class="row">
    <br/>
    <g:if test="${result?.message}">
        <p class="text text-success bg-primary">${result.message}</p>
    </g:if>
    <br/>
</div>
<div class="row">
    <br/>
    <h2>Daftar Kecamatan</h2>
    <br/>
</div>
<div class="row">
    <div class="col-md-8">
        <table>
            <col width="190"/>
            <col width="190"/>
            <col width="450"/>
            <tr>
                <th>Kecamatan</th>
                <th>Kota</th>
                <th>Deskripsi</th>

            </tr>
            <g:each var="d" in="${district}">
                <tr>
                    <td>
                        <g:link action="showDetail" params="${d}">${d.nama}</g:link>
                    </td>
                    <td>${d.city.nama}</td>
                    <td>${d.deskripsi}</td>
                </tr>
            </g:each>

        </table>
    </div>
    <div class="col-md-4">
        <div class="row"><br/>
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
            <a href="/district/create" class="btn btn-primary">Tambah Kecamatan</a>
        </div>
    </div>

</div>



</body>
</html>