package ua.artcode.dao;

import ua.artcode.model.User;
import ua.artcode.model.UserPost;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by root on 16.06.2015.
 */
public class HibernatePostDAO implements PostDao {
    EntityManager em = null;
    public HibernatePostDAO() {
        em = HibernateUtils.getEntityManager();
    }

    public UserPost createPost(User user, UserPost post) {
        java.sql.Date creationTime = new java.sql.Date(post.getCreationDate().getTime());



        return null;
    }

    public UserPost getById(long id) {
        return null;
    }

    public List<UserPost> userPosts(long id) {
        return null;
    }
}
