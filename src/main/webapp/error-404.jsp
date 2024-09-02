<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error 404 - Página no encontrada</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #d9534f;
        }
        .error-container {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .back-link {
            color: #337ab7;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error 404 - Página no encontrada</h1>
        <p>Lo sentimos, la página que estás buscando no se pudo encontrar.</p>
        <p>Por favor, verifica la URL o regresa a la <a href="<%= request.getContextPath() %>/" class="back-link">página de inicio</a>.</p>
    </div>
</body>
</html>


