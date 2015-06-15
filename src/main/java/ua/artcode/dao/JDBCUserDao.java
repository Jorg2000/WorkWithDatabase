package ua.artcode.dao;

import ua.artcode.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by serhii on 30.05.15.
 */
public class JDBCUserDao implements UserDao {
    //private Connection conn;
    private ConnectionFactory connectionFactory;

    public JDBCUserDao() {
         connectionFactory = new ConnectionFactory();
        }

    public User create(User user) {

       Connection conn = connectionFactory.createConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (fullname, email, pass) VALUES (?,?,?)");
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPass());
            ps.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findByEmail(String email) {

        Converter convert = new Converter();

        User user = null;
        Connection conn = connectionFactory.createConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id, fullname, email, pass FROM users WHERE email = ?");
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();
            user = convert.resultToUser(resultSet);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findById(long id) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Converter convert = new Converter();
        User user = null;
        Connection conn = connectionFactory.createConnection();
        try {

            PreparedStatement ps = conn.prepareStatement("SELECT id, fullname, email, pass FROM users WHERE " +
                    " id = ?");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            user = convert.resultToUser(resultSet);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
  }

    public void delete(long id) {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        Connection conn = connectionFactory.createConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?") ;
            ps.setLong(1,id);
            ps.execute();
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
        Converter convert = new Converter();
        Connection conn;
        conn = connectionFactory.createConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, fullname, email, pass FROM users;");
            users = convert.resultToUsersList(resultSet);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
