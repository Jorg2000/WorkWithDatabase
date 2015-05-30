package ua.artcode.dao;

import ua.artcode.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by serhii on 30.05.15.
 */
public class JDBCUserDao implements UserDao {
    private Connection conn;
    private ConnectionFactory connectionFactory;

    public JDBCUserDao() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        }

    public User create(User user) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn;

        conn = connectionFactory.createConnection("127.0.0.1","3306","notes","root","root");
        try {
            Statement statement = conn.createStatement();

            boolean resultSet = statement.execute(
                    "INSERT INTO users (fullname, email, pass)" +
                    "VALUES ('" + user.getFullName() + "','" + user.getEmail() + "','" + user.getPass() +"');");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findByEmail(String email) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn;
        User user = null;
        conn = connectionFactory.createConnection("127.0.0.1","3306","notes","root","root");

        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id, fullname, email FROM users WHERE " +
                    " email = '" + email + "';");

            while (resultSet.next()){ // row - object => relation -> oop model
                long id = resultSet.getLong("id");
                String fullname = resultSet.getString("fullname");
                String pass = resultSet.getString("pass");
                user = new User(id, fullname, email, pass);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findById(long id) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn;
        User user = null;
        conn = connectionFactory.createConnection("127.0.0.1","3306","notes","root","root");
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id, fullname, email, pass FROM users WHERE " +
                    " id = '" + id + "';");

            while (resultSet.next()){ // row - object => relation -> oop model

                String fullname = resultSet.getString("fullname");
                String pass = resultSet.getString("pass");
                String email = resultSet.getString("email");
                user = new User(id, fullname, email, pass);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
  }

    public void delete(long id) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn;
        conn = connectionFactory.createConnection("127.0.0.1","3306","notes","root","root");
        try {
            Statement statement = conn.createStatement();
            System.out.println("DELETE FROM users WHERE id ='"+ id + "';");
            statement.execute("DELETE FROM users WHERE id ='"+ id + "';");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(User user) {

    }

    public List<User> findAll() {
        List<User> users = new LinkedList<User>();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn;
        conn = connectionFactory.createConnection("127.0.0.1","3306","notes","root","root");

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, fullname, email FROM users;");

            while (resultSet.next()){ // row - object => relation -> oop model
                long id = resultSet.getLong("id");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                users.add(new User(id,fullname,email, null));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
