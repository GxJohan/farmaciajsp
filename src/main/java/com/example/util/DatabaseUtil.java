package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {

    // Nombre del archivo de propiedades que contiene la configuración de la base de datos
    private static final String PROPERTIES_FILE = "/db.properties";
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;

    // Bloque estático que se ejecuta cuando se carga la clase para inicializar las propiedades de la base de datos
    static {
        loadDatabaseProperties();
    }

    // Metodo privado para cargar las propiedades de la base de datos desde un archivo de configuración
    private static void loadDatabaseProperties() {

        Properties prop = new Properties(); // Objeto para manejar las propiedades
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            // Carga el archivo de propiedades usando el ClassLoader
            if (input == null) {
                System.out.println("No se puede encontrar el archivo " + PROPERTIES_FILE);
                return;
            }
            prop.load(input); // Carga las propiedades desde el archivo
            // Asigna las propiedades a las variables estáticas
            jdbcURL = prop.getProperty("jdbcURL");
            jdbcUsername = prop.getProperty("jdbcUsername");
            jdbcPassword = prop.getProperty("jdbcPassword");

        } catch (IOException e) {
            e.printStackTrace(); // Maneja cualquier excepción de E/S
        }

    }

    // Metodo público para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        try {
            // Carga el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Si no se puede cargar el driver, lanza una excepción de SQL
            throw new SQLException("No se pudo cargar el driver de MySQL", e);
        }
        // Retorna una conexión a la base de datos usando las propiedades cargadas
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

}
