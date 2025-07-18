<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.devopsyne.model.Solicitante" %>
<%
    String error = (String) request.getAttribute("error");
    Solicitante solicitante = (Solicitante) request.getAttribute("solicitante");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Solicitante - Devopsyne</title>
    <link rel="stylesheet" href="css/estilos.css">
    <style>
        body {
            background-color: #f1f4f8;
            font-family: Arial, sans-serif;
        }

        .contenedor {
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px #ccc;
        }

        h2 {
            text-align: center;
            color: #1b365c;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .boton {
            background-color: #1b365c;
            color: white;
            padding: 10px;
            margin-top: 25px;
            width: 100%;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .boton:hover {
            background-color: #284b7d;
        }

        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }

        .volver {
            margin-top: 20px;
            text-align: center;
        }

        .volver a {
            color: #1b365c;
            text-decoration: none;
        }

        .volver a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="contenedor">
    <h2>Registro de Solicitante</h2>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="registro" method="post">
        <label for="nombre">Nombre completo:</label>
        <input type="text" id="nombre" name="nombre" required value="<%= solicitante != null ? solicitante.getNombre() : "" %>">

        <label for="identificacion">Identificación:</label>
        <input type="text" id="identificacion" name="identificacion" required value="<%= solicitante != null ? solicitante.getIdentificacion() : "" %>">

        <label for="email">Correo electrónico:</label>
        <input type="email" id="email" name="email" required value="<%= solicitante != null ? solicitante.getEmail() : "" %>">

        <label for="telefono">Teléfono:</label>
        <input type="text" id="telefono" name="telefono" value="<%= solicitante != null ? solicitante.getTelefono() : "" %>">

        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" value="<%= solicitante != null ? solicitante.getDireccion() : "" %>">

        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit" class="boton">Registrarse</button>
    </form>

    <div class="volver">
        <a href="login.jsp">← Ya tengo cuenta</a>
    </div>
</div>

</body>
</html>
