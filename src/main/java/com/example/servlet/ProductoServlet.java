package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.dao.ProductoDAO;
import com.example.model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Anotación para mapear el servlet a la URL '/productos'
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    private static ProductoDAO productoDAO; // DAO para manejar las operaciones de productos

    // Método de inicialización del servlet, se ejecuta una vez cuando se carga el servlet
    public void init() {
        productoDAO = new ProductoDAO();
    }

    // Método para manejar solicitudes GET (lectura de datos)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion"); // Obtiene el parámetro 'accion' de la solicitud
        if (accion == null) {
            accion = "listar"; // Acción predeterminada si no se especifica ninguna
        }

        try {
            // Ejecuta diferentes acciones según el valor del parámetro 'accion'
            switch (accion) {
                case "nuevo" -> mostrarFormularioNuevo(request, response); // Muestra el formulario para un nuevo producto
                case "insertar" -> insertarProducto(request, response); // Inserta un nuevo producto en la base de datos
                case "eliminar" -> eliminarProducto(request, response); // Elimina un producto existente
                case "editar" -> mostrarFormularioEditar(request, response); // Muestra el formulario para editar un producto
                case "actualizar" -> actualizarProducto(request, response); // Actualiza un producto existente en la base de datos
                default -> listarProductos(request, response); // Lista todos los productos (acción predeterminada)
            }
        } catch (SQLException ex) {
            throw new ServletException(ex); // Maneja cualquier error de base de datos
        }
    }

    // Método para manejar solicitudes POST (envío de formularios)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Redirige las solicitudes POST al método doGet
    }

    // Método para listar todos los productos y mostrarlos en una página JSP
    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Producto> listaProductos = productoDAO.selectAllProductos(); // Obtiene todos los productos de la base de datos
        request.setAttribute("listaProductos", listaProductos); // Establece la lista de productos como atributo en la solicitud
        request.getRequestDispatcher("producto-list.jsp").forward(request, response); // Redirige a la vista 'producto-list.jsp'
    }

    // Método para mostrar el formulario de un nuevo producto
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("producto-form.jsp").forward(request, response); // Redirige a la vista 'producto-form.jsp'
    }

    // Método para insertar un nuevo producto en la base de datos
    private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Obtiene los valores del formulario
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Producto nuevoProducto = new Producto(null, nombre, precio, cantidad); // Crea un nuevo objeto Producto
        productoDAO.insertProducto(nuevoProducto); // Inserta el producto en la base de datos
        response.sendRedirect("productos"); // Redirige a la lista de productos
    }

    // Método para mostrar el formulario para editar un producto existente
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id")); // Obtiene el ID del producto a editar
        Producto productoExistente = productoDAO.selectProducto(id); // Busca el producto en la base de datos
        request.setAttribute("producto", productoExistente); // Establece el producto como atributo en la solicitud
        request.getRequestDispatcher("producto-form.jsp").forward(request, response); // Redirige a la vista 'producto-form.jsp'
    }

    // Método para actualizar un producto existente en la base de datos
    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Obtiene los valores del formulario
        Long id = Long.parseLong(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Producto producto = new Producto(id, nombre, precio, cantidad); // Crea un objeto Producto con los nuevos valores
        productoDAO.updateProducto(producto); // Actualiza el producto en la base de datos
        response.sendRedirect("productos"); // Redirige a la lista de productos
    }

    // Método para eliminar un producto de la base de datos
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id")); // Obtiene el ID del producto a eliminar
        productoDAO.deleteProducto(id); // Elimina el producto de la base de datos
        response.sendRedirect("productos"); // Redirige a la lista de productos
    }

    // Servlet adicional para manejar la inserción de productos
    @WebServlet("/insert")
    public static class InsertarProductoServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                insertarProducto(request, response); // Llama al método de inserción de productos
            } catch (SQLException e) {
                throw new ServletException(e); // Maneja cualquier error de base de datos
            }
        }

        private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            // Mismo código que en el método insertarProducto de ProductoServlet
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Producto nuevoProducto = new Producto(null, nombre, precio, cantidad);
            productoDAO.insertProducto(nuevoProducto);
            response.sendRedirect("productos");
        }
    }

    // Servlet adicional para manejar la actualización de productos
    @WebServlet("/update")
    public static class ActualizarProductoServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                actualizarProducto(request, response); // Llama al método de actualización de productos
            } catch (SQLException e) {
                throw new ServletException(e); // Maneja cualquier error de base de datos
            }
        }

        private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            // Mismo código que en el método actualizarProducto de ProductoServlet
            Long id = Long.parseLong(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Producto producto = new Producto(id, nombre, precio, cantidad);
            productoDAO.updateProducto(producto);
            response.sendRedirect("productos");
        }
    }
}
