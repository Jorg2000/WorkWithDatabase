package ua.artcode.dao;

import ua.artcode.model.User;

import java.sql.*;

/**
 * Created by root on 30.05.2015.
 */
public class ConnectionFactory {

    public Connection createConnection(String host, String port, String database, String user, String pass ) {
        Connection conn = null;

        String connectionString =
                "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + pass;

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
