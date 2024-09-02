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

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    private static ProductoDAO productoDAO; // Cambiado a static

    public void init() {
        productoDAO = new ProductoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }

        try {
            switch (accion) {
                case "nuevo" -> mostrarFormularioNuevo(request, response);
                case "insertar" -> insertarProducto(request, response);
                case "eliminar" -> eliminarProducto(request, response);
                case "editar" -> mostrarFormularioEditar(request, response);
                case "actualizar" -> actualizarProducto(request, response);
                default -> listarProductos(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Producto> listaProductos = productoDAO.selectAllProductos();
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("producto-list.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("producto-form.jsp").forward(request, response);
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Producto nuevoProducto = new Producto(null, nombre, precio, cantidad);
        productoDAO.insertProducto(nuevoProducto);
        response.sendRedirect("productos");
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Producto productoExistente = productoDAO.selectProducto(id);
        request.setAttribute("producto", productoExistente);
        request.getRequestDispatcher("producto-form.jsp").forward(request, response);
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        Producto producto = new Producto(id, nombre, precio, cantidad);
        productoDAO.updateProducto(producto);
        response.sendRedirect("productos");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        productoDAO.deleteProducto(id);
        response.sendRedirect("productos");
    }

    @WebServlet("/insert")
    public static class InsertarProductoServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                insertarProducto(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Producto nuevoProducto = new Producto(null, nombre, precio, cantidad);
            productoDAO.insertProducto(nuevoProducto);
            response.sendRedirect("productos");
        }
    }

    @WebServlet("/update")
    public static class ActualizarProductoServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                actualizarProducto(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

        private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
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
