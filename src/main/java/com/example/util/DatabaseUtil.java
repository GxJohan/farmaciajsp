package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {

    private static final String PROPERTIES_FILE = "/db.properties";
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;

    static {
        loadDatabaseProperties();
    }

    private static void loadDatabaseProperties() {

        Properties prop = new Properties();
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("No se puede encontrar el archivo " + PROPERTIES_FILE);
                return;
            }
            prop.load(input);
            jdbcURL = prop.getProperty("jdbcURL");
            jdbcUsername = prop.getProperty("jdbcUsername");
            jdbcPassword = prop.getProperty("jdbcPassword");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver de MySQL", e);
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

}
