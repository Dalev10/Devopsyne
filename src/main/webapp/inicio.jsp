<%@ include file="header.jsp" %>
<html lang="es">
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        main {
            text-align: center;
            padding: 60px 20px;
        }

        h2 {
            font-size: 2em;
            margin-bottom: 20px;
        }

        p {
            font-size: 1.2em;
            color: #444;
            margin-bottom: 20px;
        }

        .slogan {
            font-style: italic;
            color: #0077cc;
        }

        .btn-principal {
            display: inline-block;
            margin-top: 40px;
            padding: 12px 30px;
            background-color: #0077cc;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-size: 1em;
            transition: background-color 0.3s;
        }

        .btn-principal:hover {
            background-color: #005fa3;
        }
    </style>
</head>
<body>
    <main>
        <h2>Bienvenido a Devopsyne</h2>

        <p>¡Tu solución confiable para la gestión de materiales!</p>

        <p>En nuestro sistema podrás registrar, editar y consultar solicitudes de materiales de manera eficiente y segura.</p>

        <p class="slogan">"Organización y control para un futuro más productivo"</p>

        <a href="menu.jsp" class="btn-principal">Ir al Menú Principal</a>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>
