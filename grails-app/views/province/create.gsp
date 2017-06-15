<html>
<head>
    <meta name="layout" content="main">
    <title>Province</title>

</head>

<body id="add">
<div class ="row">
    <h1>Tambah Provinsi Baru</h1>
</div>
<div class="row">
    <g:form controller="province" action="create">
        <table>
            <g:if test="${result?.message}" >
                <tr>

                    <td colspan="2">
                        <p class="text text-danger bg-warning h3 ">${result.message}</p>
                    </td>
                </tr>
            </g:if>
            <tr>
                <td>
                    Nama Provinsi
                </td>
                <td>
                    <g:textField name="nama" value="${province?.nama}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Pulau
                </td>
                <td>
                    <g:textField name="pulau" value="${province?.pulau}"/>
                </td>
            </tr>
            <tr>
                <td>Deskripsi</td>
                <td><g:textArea name="deskripsi" value="${province?.deskripsi}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <g:submitButton class="btn btn-primary" name="submit">Buat</g:submitButton>
                </td>

            </tr>

        </table>
    </g:form>
</div>

</body>
</html>