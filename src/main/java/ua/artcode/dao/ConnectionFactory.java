package ua.artcode.dao;

import ua.artcode.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by root on 30.05.2015.
 */
public class ConnectionFactory {

    private String dbHost;
    private String dbUser;
    private String dbPass;
    private String dbPort;
    private String dbName;

    public ConnectionFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src\\main\\resources\\app.conf")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbHost = properties.getProperty("dbHost");
        dbPort = properties.getProperty("dbPort");
        dbName = properties.getProperty("dbName");
        dbUser = properties.getProperty("dbUser");
        dbPass = properties.getProperty("dbPass");
    }

    public Connection createConnection() {
        Connection conn = null;

        String connectionString =
                "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?user=" + dbUser + "&password=" + dbPass;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(connectionString);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return conn;
        }


    }

}
