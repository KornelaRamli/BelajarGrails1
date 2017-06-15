<html>
<head>
    <meta name="layout"content="main">
    <title>City Show</title>
</head>

<body id="list">

    <div class="row">
        <br/>
        <g:if test="${result?.message}">
            <span style="font-weight: bold;color: #cc0000">
                <g:message code="${result.message}" args="${result.args}"></g:message>
            </span>
        </g:if>
    </div>
    <div class="row">
        <br/>
        <h2>Daftar Kota</h2>
        <br/>
    </div>

    <div class="row">
        <div class="col-md-9">
            <table>
                <col width="200"/>
                <col width="200"/>
                <col width="450"/>
                <tr>
                    <th>Nama</th>
                    <th>Provinsi</th>
                    <th>Deskripsi</th>

                </tr>
                <g:each var="c" in="${city}">
                    <tr>
                        <td>
                            <g:link action="showlist" params="${c}">${c.nama}</g:link>
                        </td>
                        <td>${c.provinsi.nama}</td>
                        <td>${c.deskripsi}</td>
                    </tr>
                </g:each>

            </table>
        </div>
        <div class="col-md-3">
            <g:form action="search">
                <div class="row">
                    <h4>Search</h4>
                    <br/>

                </div>
                <div class="row">
                    <p class="text color-palette">Nama</p>
                    <g:textField name="nama"></g:textField>
                </div><br/>
                <div class="row"><br/>
                <g:actionSubmit value="Search" class="btn btn-success" />
            </g:form>


                <a href="/city/create" class="btn btn-primary">Tambah Kota</a>
            </div>
        </div>

    </div>
    <div class="row">

        <g:paginate total="${result.isicount?:0}" />
    </div>



</body>
</html>