<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Productos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
            <div>
                <a href="<%=request.getContextPath()%>/" class="navbar-brand"> Gestión de Productos </a>
            </div>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:if test="${producto != null}">
                    <form action="update" method="post">
                </c:if>
                <c:if test="${producto == null}">
                    <form action="insert" method="post">
                </c:if>

                <caption>
                    <h2>
                        <c:if test="${producto != null}">
                            Editar Producto
                        </c:if>
                        <c:if test="${producto == null}">
                            Agregar Nuevo Producto
                        </c:if>
                    </h2>
                </caption>

                <c:if test="${producto != null}">
                    <input type="hidden" name="id" value="<c:out value='${producto.id}' />" />
                </c:if>

                <fieldset class="form-group">
                    <label>Nombre del Producto</label>
                    <input type="text" value="<c:out value='${producto.nombre}' />" class="form-control" name="nombre" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Precio del Producto</label>
                    <input type="text" value="<c:out value='${producto.precio}' />" class="form-control" name="precio">
                </fieldset>

                <fieldset class="form-group">
                    <label>Cantidad del Producto</label>
                    <input type="text" value="<c:out value='${producto.cantidad}' />" class="form-control" name="cantidad">
                </fieldset>

                <button type="submit" class="btn btn-success">Guardar</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>