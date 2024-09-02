<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Productos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
            <div>
                <a href="<%=request.getContextPath()%>/" class="navbar-brand">Gestión de Productos</a>
            </div>
        </nav>
    </header>
    <br>
    <div class="container">
        <h2>Lista de Productos</h2>
        <a href="productos?accion=nuevo" class="btn btn-primary mb-3">Agregar Nuevo Producto</a>
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
                <c:forEach var="producto" items="${listaProductos}">
                    <tr>
                        <td><c:out value="${producto.id}" /></td>
                        <td><c:out value="${producto.nombre}" /></td>
                        <td><c:out value="${producto.precio}" /></td>
                        <td><c:out value="${producto.cantidad}" /></td>
                        <td>
                            <a href="productos?accion=editar&id=${producto.id}" class="btn btn-warning">Editar</a>
                            <a href="productos?accion=eliminar&id=${producto.id}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar este producto?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
