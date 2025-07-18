<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Devopsyne - Iniciar Sesión</title>
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
        .contenido {
            padding: 30px;
        }
        .login-box {
            background-color: #ffffff;
            max-width: 400px;
            margin: 40px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .login-box h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-box label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        .login-box input[type="text"],
        .login-box input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-box input[type="submit"] {
            margin-top: 25px;
            width: 100%;
            padding: 10px;
            background-color: #1b365c;
            color: white;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }
        .login-box input[type="submit"]:hover {
            background-color: #0f2540;
        }
        .error {
            color: red;
            margin-top: 15px;
            text-align: center;
        }
        .registro-link {
            text-align: center;
            margin-top: 20px;
        }
        .registro-link a {
            color: #1b365c;
            text-decoration: none;
            font-weight: bold;
        }
        .registro-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<header>
    <h1>Devopsyne</h1>
    <p>Gestión de Solicitudes del Depósito de Materiales</p>
</header>

<div class="contenido">

    <div class="login-box">
        <h2>Iniciar Sesión</h2>

        <form action="login" method="post">
            <label for="email">Correo electrónico:</label>
            <input type="text" id="email" name="email" required>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Ingresar">
        </form>

        <%
            String errorMsg = (String) request.getAttribute("error");
            if (errorMsg != null) {
        %>
            <div class="error"><%= errorMsg %></div>
        <%
            }
        %>

        <div class="registro-link">
            ¿No tienes una cuenta? <a href="registro.jsp">Regístrate aquí</a>
        </div>
    </div>

</div>

<footer style="background-color: #1b365c; color: white; text-align: center; padding: 15px; margin-top: 20px;">
    <p>&copy; 2025 Devopsyne. Todos los derechos reservados.</p>
</footer>

</body>
</html>
