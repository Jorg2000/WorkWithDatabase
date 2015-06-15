package ua.artcode.dao;

import javafx.geometry.Pos;
import ua.artcode.model.User;
import ua.artcode.model.UserPost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 04.06.2015.
 */


public class JDBCPostDAO implements PostDao {
    private ConnectionFactory connectionFactory;

    public JDBCPostDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public UserPost createPost(User u, UserPost p) {
        User user = u;
        UserPost userPost = p;
        java.sql.Date creationTime = new java.sql.Date(userPost.getCreationDate().getTime());

        Connection conn = connectionFactory.createConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO posts " +
                    "(post_text, post_data_time, user_id, post_title)  " +
                    "VALUES (?,?,?,?) ");
            ps.setString(1,userPost.getBody());
            ps.setDate(2, creationTime);
            ps.setLong(3, userPost.getOwner().getId());
            ps.setString(4, userPost.getTitle());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userPost;
    }

    public UserPost getById(long id) {

        Connection conn = connectionFactory.createConnection();
        Converter convert = new Converter();
        ResultSet userResultSet = null;
        ResultSet postResultSet = null;
        try {
            PreparedStatement transactionBegin = conn.prepareStatement("START TRANSACTION;");
            transactionBegin.executeQuery();
            PreparedStatement selectPost = conn.prepareStatement("SELECT * from posts WHERE post_id = ?");
            selectPost.setLong(1, id);
            postResultSet = selectPost.executeQuery();
            PreparedStatement selectUser = conn.prepareStatement("SELECT * from users WHERE id = ?");
            if (postResultSet.next()) {
                selectUser.setLong(1, postResultSet.getLong("user_id"));
                userResultSet = selectUser.executeQuery();
                selectPost = conn.prepareStatement("SELECT * from posts WHERE post_id = ?");
                selectPost.setLong(1, id);
                postResultSet = selectPost.executeQuery();
            }
            PreparedStatement transactionCommit = conn.prepareStatement("COMMIT;");
            transactionCommit.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return convert.resultToPost(postResultSet,userResultSet);

    }

    public List<UserPost>  userPosts(long UserId) {
        Connection conn = connectionFactory.createConnection();
        Converter convert = new Converter();
        ResultSet postsResultSet = null;
        ResultSet userResaultSet = null;

        try {
            PreparedStatement userPosts = conn.prepareStatement("SELECT * FROM posts WHERE user_id = ?");
            userPosts.setLong(1, UserId);
            postsResultSet = userPosts.executeQuery();
            userResaultSet = getUserById(UserId);
            return convert.resultToPostsList(postsResultSet,userResaultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getUserById(long id) {
        Connection conn = connectionFactory.createConnection();
        Converter convert = new Converter();
        ResultSet userResultSet = null;
        PreparedStatement selectUser = null;
        try {
            selectUser = conn.prepareStatement("SELECT * from users WHERE id = ?");
            selectUser.setLong(1, id);
            userResultSet = selectUser.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userResultSet;
    }

}
