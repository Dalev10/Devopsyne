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
    <title>Devopsyne - Inicio</title>
    <link rel="stylesheet" href="css/estilos.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f2f2f2;
        }
        header {
            background-color: #1b365c;
            color: white;
            padding: 20px;
            text-align: center;
        }
        nav {
            background-color: #293e63;
            padding: 10px;
            text-align: center;
        }
        nav a {
            color: white;
            margin: 0 15px;
            text-decoration: none;
            font-weight: bold;
        }
        nav a:hover {
            text-decoration: underline;
        }
        .contenido {
            padding: 30px;
        }
        .bienvenida {
            background-color: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .bienvenida h2 {
            color: #1b365c;
        }
        .acciones a {
            display: inline-block;
            margin: 20px 15px;
            padding: 12px 25px;
            background-color: #1b365c;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: background 0.3s ease;
        }
        .acciones a:hover {
            background-color: #293e63;
        }
    </style>
</head>
<body>

<header>
    <h1>Devopsyne</h1>
    <p>Gestión de Solicitudes del Depósito de Materiales</p>
</header>

<nav>
    <a href="ver_solicitudes">Solicitudes</a>
    <a href="nueva-solicitud.jsp">Nueva Solicitud</a>
    <a href="logout">Cerrar Sesión</a>
</nav>

<div class="contenido">
    <div class="bienvenida">
        <h2>¡Bienvenido(a), <%= usuario.getNombre() %>!</h2>
        <p>Has iniciado sesión como solicitante registrado.</p>

        <div class="acciones">
            <a href="ver_solicitudes">Ver Mis Solicitudes</a>
            <a href="nueva-solicitud.jsp">Realizar Nueva Solicitud</a>
        </div>
    </div>
</div>

<footer style="background-color: #1b365c; color: white; text-align: center; padding: 15px; margin-top: 20px;">
    <p>&copy; 2025 Devopsyne. Todos los derechos reservados.</p>
</footer>

</body>
</html>
