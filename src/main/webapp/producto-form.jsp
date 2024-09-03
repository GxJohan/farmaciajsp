<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
Esta directiva de página JSP define el lenguaje de la página como Java, el tipo de contenido
como HTML con codificación UTF-8, y declara la biblioteca de etiquetas JSTL (JavaServer Pages Standard Tag Library)
con el prefijo "c" para usar sus funciones en la página.
-->
<html>
<head>
    <title>Gestión de Productos</title>
    <!-- Enlaza el archivo de estilos de Bootstrap para darle un estilo a la página -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/" class="navbar-brand"> Gestión de Productos </a>
            <!--
                Este enlace es la marca de la aplicación en la barra de navegación. Utiliza
                `request.getContextPath()` para generar un enlace a la raíz de la aplicación.
            -->
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <!--
                Verifica si el objeto `producto` no es nulo. Si no es nulo, significa que
                estamos en modo de edición de un producto existente.
            -->
            <c:if test="${producto != null}">
            <form action="update" method="post">
                <!--
                    Si estamos editando un producto, se envía el formulario al servlet que maneja la
                    actualización (`update`).
                -->
                </c:if>
                <c:if test="${producto == null}">
                <form action="insert" method="post">
                    <!--
                        Si `producto` es nulo, significa que estamos creando un nuevo producto,
                        por lo que se envía el formulario al servlet que maneja la inserción (`insert`).
                    -->
                    </c:if>

                    <caption>
                        <h2>
                            <!--
                                Muestra el título adecuado dependiendo de si estamos en modo de edición o de inserción.
                            -->
                            <c:if test="${producto != null}">
                                Editar Producto
                            </c:if>
                            <c:if test="${producto == null}">
                                Agregar Nuevo Producto
                            </c:if>
                        </h2>
                    </caption>

                    <!--
                        Si `producto` no es nulo, estamos en modo de edición, por lo que necesitamos
                        enviar el ID del producto como un campo oculto para identificar qué producto
                        se está editando.
                    -->
                    <c:if test="${producto != null}">
                        <input type="hidden" name="id" value="<c:out value='${producto.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Nombre del Producto</label>
                        <!--
                            Campo de entrada para el nombre del producto. Si estamos en modo de edición,
                            muestra el nombre actual del producto.
                        -->
                        <input type="text" value="<c:out value='${producto.nombre}' />" class="form-control" name="nombre" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Precio del Producto</label>
                        <!--
                            Campo de entrada para el precio del producto. Si estamos en modo de edición,
                            muestra el precio actual del producto.
                        -->
                        <input type="text" value="<c:out value='${producto.precio}' />" class="form-control" name="precio">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Cantidad del Producto</label>
                        <!--
                            Campo de entrada para la cantidad del producto. Si estamos en modo de edición,
                            muestra la cantidad actual del producto.
                        -->
                        <input type="text" value="<c:out value='${producto.cantidad}' />" class="form-control" name="cantidad">
                    </fieldset>

                    <!-- Botón para enviar el formulario -->
                    <button type="submit" class="btn btn-success">Guardar</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
