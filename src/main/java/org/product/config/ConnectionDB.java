package org.product.config;
import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;


public class ConnectionDB {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("URL");
    private static final String USERNAME = dotenv.get("USERNAME");
    private static final String PASSWORD = dotenv.get("PASSWORD");

    public static Connection connection(){
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Hubo un error conectadon con la base de datos", e);
        }
    }
}