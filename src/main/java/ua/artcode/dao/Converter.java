package ua.artcode.dao;

import ua.artcode.model.User;
import ua.artcode.model.UserPost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 31.05.2015.
 */
public class Converter {

    public User resultToUser(ResultSet userResultSet)  {
    User user = null;
        try {
                if (userResultSet.next()) {
                    long id = userResultSet.getLong("id");
                    String fullname = userResultSet.getString("fullname");
                    String pass = userResultSet.getString("pass");
                    String email = userResultSet.getString("email");
                    user = new User(id, fullname, email, pass);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public UserPost resultToPost(ResultSet postResultSet, ResultSet userResultSet) {
        try {
            User user = null;
            if (postResultSet.next()) {
                long id = postResultSet.getLong("post_id");
                String body = postResultSet.getString("post_text");
                Date date = postResultSet.getDate("post_data_time");
                String title = postResultSet.getString("post_title");

                    user = resultToUser(userResultSet);

                return new UserPost(title,body,user,date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> resultToUsersList(ResultSet resultSet)  {
        LinkedList<User> users = new LinkedList<User>();
        try {
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String fullname = resultSet.getString("fullname");
                String pass = resultSet.getString("pass");
                String email = resultSet.getString("email");
                users.add(new User(id, fullname, email, pass));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<UserPost> resultToPostsList (ResultSet postsResultSet, ResultSet userResaultSet) {
        LinkedList<UserPost> posts = new LinkedList<UserPost>();
        User user = resultToUser(userResaultSet);;
        try {
            while (postsResultSet.next()){
                long id = postsResultSet.getLong("post_id");
                String title = postsResultSet.getString("post_title");
                String body = postsResultSet.getString("post_text");
                Date date = postsResultSet.getDate("post_data_time");
                posts.add(new UserPost(title,body,user,date));
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
