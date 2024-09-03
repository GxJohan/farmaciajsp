<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Directiva JSP que indica el lenguaje utilizado (Java), el tipo de contenido que se generará (HTML) y la codificación de caracteres (UTF-8) tanto para el archivo JSP como para la salida HTML --%>

<%@ page isErrorPage="true" %>
<%-- Directiva JSP crucial que marca esta página como la encargada de manejar errores. El servidor la mostrará automáticamente cuando ocurra un error 500 (Error Interno del Servidor) --%>

<!DOCTYPE html>
<html lang="es">
<%-- Declaración del tipo de documento (HTML5) e indicación del idioma principal del contenido (español) --%>

<head>
    <meta charset="UTF-8"> <%-- Establece la codificación de caracteres UTF-8 para asegurar la correcta visualización de caracteres especiales --%>
    <meta name="viewport" content="width=device-width, initial-scale=1"> <%-- Configura la página para que se adapte a diferentes tamaños de pantalla (responsive design) --%>
    <title>Error 500 - Error Interno del Servidor</title> <%-- Define el título que se mostrará en la barra del navegador o pestaña --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <%-- Incluye Bootstrap, un framework CSS que facilita el diseño y la maquetación, desde una red de entrega de contenidos (CDN) --%>
</head>

<body>
<div class="container mt-5"> <%-- Contenedor principal con margen superior (mt-5) utilizando clases de Bootstrap --%>
    <div class="row"> <%-- Fila para organizar el contenido dentro del contenedor --%>
        <div class="col-md-6 offset-md-3 text-center">
            <%-- Columna que ocupa 6 de 12 espacios en pantallas medianas (md) y está centrada horizontalmente (offset-md-3 y text-center) --%>
            <h1 class="display-1">500</h1> <%-- Encabezado grande (display-1) mostrando el código de error --%>
            <h2 class="mb-3">Error Interno del Servidor</h2> <%-- Encabezado (h2) con margen inferior (mb-3) describiendo el error --%>
            <p class="lead mb-4">Lo sentimos, ha ocurrido un error inesperado en el servidor. Por favor, inténtelo de nuevo más tarde.</p>
            <%-- Párrafo explicativo con margen inferior (mb-4) utilizando la clase "lead" de Bootstrap para un texto más destacado --%>
            <a href="<%= request.getContextPath() %>/" class="btn btn-primary">Volver a la página principal</a>
            <%-- Enlace (botón) que utiliza request.getContextPath() para llevar al usuario de vuelta a la raíz de la aplicación, con estilo de Bootstrap (btn btn-primary) --%>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<%-- Incluye el JavaScript de Bootstrap para habilitar sus funcionalidades interactivas (aunque en esta página de error simple, puede que no haya mucho que hacer) --%>
</body>

</html>