package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Producto;
import com.example.util.DatabaseUtil;

public class ProductoDAO {

    // Consultas SQL para realizar las operaciones CRUD en la tabla 'producto'
    private static final String INSERT_PRODUCTO_SQL = "INSERT INTO producto (nombre, precio, cantidad) VALUES (?, ?, ?)";
    private static final String SELECT_PRODUCTO_BY_ID = "SELECT id, nombre, precio, cantidad FROM producto WHERE id = ?";
    private static final String SELECT_ALL_PRODUCTOS = "SELECT * FROM producto";
    private static final String DELETE_PRODUCTO_SQL = "DELETE FROM producto WHERE id = ?";
    private static final String UPDATE_PRODUCTO_SQL = "UPDATE producto SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?";

    // Metodo para insertar un nuevo producto en la base de datos
    public void insertProducto(Producto producto) throws SQLException {
        // Establece una conexión y prepara la sentencia SQL para insertar un producto
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTO_SQL)) {
            // Configura los valores de los parámetros en la sentencia SQL
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setDouble(2, producto.getPrecio());
            preparedStatement.setInt(3, producto.getCantidad());
            // Ejecuta la sentencia SQL de inserción
            preparedStatement.executeUpdate();
        }
    }

    // Metodo para seleccionar un producto específico por su ID
    public Producto selectProducto(Long id) throws SQLException {
        Producto producto = null;
        // Establece una conexión y prepara la sentencia SQL para seleccionar un producto por su ID
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTO_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            // Si se encuentra un producto con el ID dado, crea un objeto Producto con los datos obtenidos
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                producto = new Producto(id, nombre, precio, cantidad);
            }
        }
        return producto; // Retorna el producto encontrado o null si no se encontró
    }

    // Metodo para seleccionar todos los productos de la base de datos
    public List<Producto> selectAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        // Establece una conexión y prepara la sentencia SQL para seleccionar todos los productos
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            // Itera sobre los resultados y crea objetos Producto para cada registro
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                productos.add(new Producto(id, nombre, precio, cantidad));
            }
        }
        return productos; // Retorna la lista de productos
    }

    //Metodo para eliminar un producto por su ID
    public boolean deleteProducto(Long id) throws SQLException {
        boolean rowDeleted;
        // Establece una conexión y prepara la sentencia SQL para eliminar un producto por su ID
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTO_SQL)) {
            statement.setLong(1, id);
            // Ejecuta la sentencia SQL de eliminación y verifica si se eliminó alguna fila
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted; // Retorna true si se eliminó una fila, false si no
    }

    // Metodo para actualizar un producto existente en la base de datos
    public boolean updateProducto(Producto producto) throws SQLException {
        boolean rowUpdated;
        // Establece una conexión y prepara la sentencia SQL para actualizar un producto
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTO_SQL)) {
            // Configura los valores de los parámetros en la sentencia SQL
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getCantidad());
            statement.setLong(4, producto.getId());

            // Ejecuta la sentencia SQL de actualización y verifica si se actualizó alguna fila
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated; // Retorna true si se actualizó una fila, false si no
    }
}
