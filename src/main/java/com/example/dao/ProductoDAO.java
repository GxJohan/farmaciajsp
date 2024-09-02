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
    
    private static final String INSERT_PRODUCTO_SQL = "INSERT INTO producto (nombre, precio, cantidad) VALUES (?, ?, ?)";
    private static final String SELECT_PRODUCTO_BY_ID = "SELECT id, nombre, precio, cantidad FROM producto WHERE id = ?";
    private static final String SELECT_ALL_PRODUCTOS = "SELECT * FROM producto";
    private static final String DELETE_PRODUCTO_SQL = "DELETE FROM producto WHERE id = ?";
    private static final String UPDATE_PRODUCTO_SQL = "UPDATE producto SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?";

    public void insertProducto(Producto producto) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTO_SQL)) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setDouble(2, producto.getPrecio());
            preparedStatement.setInt(3, producto.getCantidad());
            preparedStatement.executeUpdate();
        }
    }

    public Producto selectProducto(Long id) throws SQLException {
        Producto producto = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTO_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                producto = new Producto(id, nombre, precio, cantidad);
            }
        }
        return producto;
    }

    public List<Producto> selectAllProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                productos.add(new Producto(id, nombre, precio, cantidad));
            }
        }
        return productos;
    }

    public boolean deleteProducto(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTO_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProducto(Producto producto) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTO_SQL)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getCantidad());
            statement.setLong(4, producto.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
