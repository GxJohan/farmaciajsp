<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Declaraciones iniciales para configurar la página JSP -->
<!-- 'page' indica que el lenguaje es Java, el tipo de contenido es HTML con codificación UTF-8 -->
<!-- 'taglib' importa la biblioteca de etiquetas JSTL (JavaServer Pages Standard Tag Library) para usar expresiones y bucles en JSP -->

<html>
<head>
    <title>Lista de Productos</title>
    <!-- Incluye el framework Bootstrap para estilos CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <!-- Barra de navegación superior -->
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <!-- Enlace al inicio de la aplicación -->
            <a href="<%=request.getContextPath()%>/" class="navbar-brand">Gestión de Productos</a>
            <!-- 'request.getContextPath()' obtiene el contexto de la aplicación para construir la URL -->
        </div>
    </nav>
</header>
<br>
<div class="container">
    <h2>Lista de Productos</h2>
    <!-- Enlace para agregar un nuevo producto -->
    <a href="productos?accion=nuevo" class="btn btn-primary mb-3">Agregar Nuevo Producto</a>
    <!-- 'productos?accion=nuevo' redirige a la acción 'nuevo' en el servlet 'ProductoServlet', que muestra el formulario de nuevo producto -->

    <!-- Tabla para mostrar la lista de productos -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <!-- Bucle para iterar sobre la lista de productos -->
        <c:forEach var="producto" items="${listaProductos}">
            <!-- 'listaProductos' es un atributo establecido en el servlet 'ProductoServlet' -->
            <tr>
                <!-- Muestra los detalles de cada producto -->
                <td><c:out value="${producto.id}" /></td>
                <td><c:out value="${producto.nombre}" /></td>
                <td><c:out value="${producto.precio}" /></td>
                <td><c:out value="${producto.cantidad}" /></td>
                <td>
                    <!-- Enlaces para editar o eliminar productos -->
                    <a href="productos?accion=editar&id=${producto.id}" class="btn btn-warning">Editar</a>
                    <!-- 'productos?accion=editar&id=${producto.id}' redirige a la acción 'editar' en 'ProductoServlet' para editar el producto -->

                    <a href="productos?accion=eliminar&id=${producto.id}" class="btn btn-danger"
                       onclick="return confirm('¿Estás seguro de que deseas eliminar este producto?');">Eliminar</a>
                    <!-- 'productos?accion=eliminar&id=${producto.id}' redirige a la acción 'eliminar' en 'ProductoServlet' para eliminar el producto -->
                    <!-- 'onclick="return confirm(...)' muestra un cuadro de confirmación antes de eliminar -->
                </td>
            </tr>
        </c:forEach>
        <!-- 'c:forEach' es una etiqueta de JSTL para iterar sobre la lista de productos -->
        </tbody>
    </table>
</div>
</body>
</html>
