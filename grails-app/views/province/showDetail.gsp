<html>
<head>
    <meta name="layout" content="main">
    <title>Province</title>
</head>

<body id="showlist">
<div class="row">
    <br/>
    <g:if test="${result?.message}" >
        <p class="text text-danger bg-warning h3 ">${result.message}</p>
    </g:if>
</div>
<div class="row text-primary">
    <h1>
        ${province.nama}
    </h1>
</div>
<div class="row">
    <table>
        <tr>
            <td>Pulau</td>
            <td>${province.pulau}</td>
        </tr>
        <tr>
            <td>Deskripsi</td>
            <td> ${province.deskripsi}</td>
        </tr>
        <tr>
            <td>Daftar Kota</td>
            <td>
                <g:each var="c" in="${city}">
                        ${c.nama}<br/>

                </g:each>
            </td>
        </tr>
    </table>
</div>
<div class="row">
    <g:link params="${province}" action="update" class="btn btn-success">Edit</g:link>
    <g:link params="${province}" action="delete" class="btn btn-danger" onclick="return confirm('${message(code:'default.button.delete.confirm.message',default:'Apakah anda yakin menghapus?')}')">Delete</g:link>
</div>
</body>
</html>