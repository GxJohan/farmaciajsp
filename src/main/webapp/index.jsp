<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Título de la página -->
    <title>Farmacia - Página Principal</title>
    <!-- Incluye Bootstrap para el diseño responsivo -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <!-- Título principal de la página -->
    <h1 class="mb-4">Bienvenido a la Farmacia</h1>
    <!-- Descripción de la página -->
    <p class="lead">Seleccione una opción:</p>
    <!-- Lista de opciones disponibles en la aplicación -->
    <div class="list-group">
        <!--
            Enlace a la página de gestión de productos. Al hacer clic,
            redirige al servlet `ProductoServlet` que maneja las operaciones
            de CRUD para productos.
        -->
        <a href="productos" class="list-group-item list-group-item-action">Gestionar Productos</a>
        <!--
            Enlaces reservados para futuras funcionalidades como realizar ventas
            o ver el inventario. Estos enlaces no están asociados a ningún servlet
            por el momento (por eso el `href="#"`).
        -->
        <a href="#" class="list-group-item list-group-item-action">Realizar Venta</a>
        <a href="#" class="list-group-item list-group-item-action">Ver Inventario</a>
    </div>
</div>
<!-- Script de Bootstrap para funcionalidades interactivas -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
