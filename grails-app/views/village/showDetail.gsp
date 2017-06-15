<html>
<head>
    <meta name="layout" content="main">
    <title>Village</title>
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
        ${village.nama}
    </h1>
</div>
<div class="row">
    <table>
        <tr>
            <td>Kecamatan</td>
            <td>${village.district.nama}</td>
        </tr>
        <tr>
            <td>Deskripsi</td>
            <td> ${village.deskripsi}</td>
        </tr>
    </table>
</div>
<div class="row">
    <g:link params="${village}" action="update" class="btn btn-success">Edit</g:link>
    <g:link params="${village}" action="delete" class="btn btn-danger" onclick="return confirm('${message(code:'default.button.delete.confirm.message',default:'Apakah anda yakin menghapus?')}')">Delete</g:link>
</div>
</body>
</html>