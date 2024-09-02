<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Farmacia - Página Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Bienvenido a la Farmacia</h1>
        <p class="lead">Seleccione una opción:</p>
        <div class="list-group">
            <a href="productos" class="list-group-item list-group-item-action">Gestionar Productos</a>
            <a href="#" class="list-group-item list-group-item-action">Realizar Venta</a>
            <a href="#" class="list-group-item list-group-item-action">Ver Inventario</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
