<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%-- Esta primera línea combina varias directivas JSP:
    * language="java": Indica que el lenguaje utilizado en esta página es Java (aunque en este caso no hay código Java explícito).
    * contentType="text/html; charset=UTF-8": Especifica que el contenido que se generará es HTML y que se utilizará la codificación de caracteres UTF-8.
    * pageEncoding="UTF-8": Establece que el archivo JSP en sí está codificado en UTF-8.
    * isErrorPage="true": Esta directiva crucial marca esta página como la encargada de manejar errores 404 (Página no encontrada). El servidor la mostrará automáticamente cuando un usuario intente acceder a una página que no existe.
--%>

<!DOCTYPE html>
<html> <%-- Etiqueta raíz que inicia el documento HTML --%>
<head>
    <meta charset="UTF-8"> <%-- Establece la codificación de caracteres UTF-8 para asegurar la correcta visualización de caracteres especiales --%>
    <title>Error 404 - Página no encontrada</title> <%-- Define el título que se mostrará en la barra del navegador o pestaña --%>

    <style> <%-- Sección donde se define el estilo CSS de la página, incrustado directamente en el HTML --%>
    body {
        font-family: Arial, sans-serif; /* Establece la fuente principal del texto */
        background-color: #f4f4f4; /* Color de fondo gris claro */
        color: #333; /* Color de texto gris oscuro */
        text-align: center; /* Centra el texto horizontalmente */
        padding: 50px; /* Añade espacio alrededor del contenido */
    }
    h1 {
        color: #d9534f; /* Color rojo para el encabezado, indicando un error */
    }
    .error-container {
        background-color: #fff; /* Fondo blanco para el contenedor principal */
        border-radius: 5px; /* Bordes ligeramente redondeados */
        padding: 20px; /* Espacio interior en el contenedor */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Sombra sutil para darle profundidad */
    }
    .back-link {
        color: #337ab7; /* Color azul para el enlace */
        text-decoration: none; /* Elimina el subrayado por defecto de los enlaces */
    }
    .back-link:hover {
        text-decoration: underline; /* Añade subrayado al pasar el ratón sobre el enlace */
    }
    </style>
</head>

<body>
<div class="error-container"> <%-- Contenedor principal que engloba el contenido de error --%>
    <h1>Error 404 - Página no encontrada</h1> <%-- Encabezado indicando el error --%>
    <p>Lo sentimos, la página que estás buscando no se pudo encontrar.</p> <%-- Mensaje explicativo --%>
    <p>Por favor, verifica la URL o regresa a la
        <a href="<%= request.getContextPath() %>/" class="back-link">página de inicio</a>.
        <%-- Enlace a la página de inicio utilizando request.getContextPath() para obtener la raíz de la aplicación, con la clase "back-link" para aplicar el estilo definido anteriormente --%>
    </p>
</div>
</body>
</html>