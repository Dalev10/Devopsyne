<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.devopsyne.model.Solicitante" %>
<%
    HttpSession sesion = request.getSession(false);
    Solicitante usuario = (Solicitante) sesion.getAttribute("usuario");

    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nueva Solicitud</title>
    <link rel="stylesheet" href="css/estilos.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #1b365c;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .formulario {
            background-color: white;
            width: 60%;
            margin: 40px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px #ccc;
        }

        .formulario h2 {
            color: #1b365c;
            margin-bottom: 20px;
            text-align: center;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="number"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .material-row {
            border-bottom: 1px solid #ddd;
            margin-bottom: 10px;
            padding-bottom: 10px;
        }

        button {
            margin-top: 25px;
            padding: 12px 20px;
            background-color: #1b365c;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        button:hover {
            background-color: #293e63;
        }

        .volver {
            display: block;
            margin: 20px auto;
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

<header>
    <h1>Crear Nueva Solicitud</h1>
</header>

<div class="formulario">
    <h2>Formulario de Solicitud</h2>
    <form action="nueva-solicitud" method="post">
        <!-- ID del solicitante oculto -->
        <input type="hidden" name="solicitante" value="<%= usuario.getId() %>">

        <label for="fecha">Fecha requerida</label>
        <input type="date" name="fecha" id="fecha" required>

        <div class="material-row">
            <label for="material_id">Selecciona Material</label>
            <select name="material_id" required>
                
                <option value="1">Cemento</option>
                <option value="2">Arena</option>
                <option value="3">Ladrillos</option>
            </select>

            <label for="cantidad">Cantidad</label>
            <input type="number" name="cantidad" min="1" required>
        </div>

       

        <button type="submit">Enviar Solicitud</button>
    </form>

    <div class="volver">
        <a href="index.jsp">← Volver al Inicio</a>
    </div>
</div>

</body>
</html>
