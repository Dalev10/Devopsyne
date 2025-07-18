<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Solicitudes</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 2em;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 1em;
        }
        th, td {
            padding: 0.6em;
            border: 1px solid #ccc;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h2>Listado de Solicitudes</h2>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>

<c:choose>
    <c:when test="${not empty solicitudes}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Solicitante ID</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${solicitudes}">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.solicitante.id}</td>
                        <td><fmt:formatDate value="${s.fecha}" pattern="yyyy-MM-dd" /></td>
                        <td>${s.estado}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>No se encontraron solicitudes.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
