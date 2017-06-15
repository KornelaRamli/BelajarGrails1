<html>
<head>
    <meta name="layout" content="main">
    <title>Village</title>

</head>

<body id="edit">
<div class ="row">
    <h1>Edit Kelurahan ${village.nama}</h1>
</div>
<div class="row">

    <g:form action="update">
        <g:hiddenField name="id" value="${village?.id}"></g:hiddenField>
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
                    Nama Kelurahan
                </td>
                <td>
                    <g:textField name="nama" value="${village?.nama}"></g:textField>
                </td>
            </tr>

            <tr>
                <td>Kecamatan</td>
                <td>
                    <g:select name="district" from="${districtlist}" value="1" optionKey="id" optionValue="nama"/>
                </td>
            </tr>

            <tr>
                <td>Deskripsi</td>
                <td><g:textArea name="deskripsi" value="${village?.deskripsi}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <g:submitButton class="btn btn-primary" name="submit" action="showlist" params="${village}">Save</g:submitButton>
                </td>

            </tr>

        </table>

    </g:form>
</div>

</body>
</html>