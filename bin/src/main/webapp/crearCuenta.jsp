<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/global.css">
    <title>Crear Cuenta</title>
</head>
<body class="flex">
    <div class="form card flex column" style="--flex-justify: space-evenly;">
        <h1>Crear cuenta</h1>
        <form class="flex column" style="--flex-align: normal;" action="CrearJugador" method="post" >
            <label for="nombre">Nombre de usuario</label>
            <input type="text" name="nombre" id="nombre" />
            <label for="correo">Correo electrónico</label>
            <input type="text" name="correo" id="correo" />
            <label for="constrasegna">Contraseña</label>
            <input type="password" name="constrasegna" id="constrasegna" />
            <input type="submit" value="Crear cuenta" class="button" />
        </form>
        <div class="flex column">
            <h3>¿Ya tienes una cuenta?</h3>
            <a href="./inicioSesion.html">Crear cuenta</a>
        </div>
    </div>
    <%
        String error = (String) request.getAttribute("error");
        if(error != null) {
    %>    
    <div class="error" id="error">
        <div class="card flex column">
            <h3>No se ha podido realizar la operación</h3>
            <button class="button" onclick="hideError()">Continuar</button>
        </div>
    </div>
    <%
        }
    %>
    
    <script>
        function hideError() {
            const error = document.getElementById("error");
            error.style.display = "none";
        }
    </script>
</body>
