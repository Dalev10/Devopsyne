<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Devopsyne - Depósito de Materiales</title>
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
    </style>
</head>
<body>
<header>
    <h1>Devopsyne</h1>
    <p>Gestión de Solicitudes del Depósito de Materiales</p>
</header>
<nav>
    <a href="VerSolicitudesServlet">Solicitudes</a>
    <a href="NuevaSolicitudServlet">Nueva Solicitud</a>
    <a href="logout.jsp">Cerrar Sesión</a>
</nav>
<div class="contenido">
