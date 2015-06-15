package ua.artcode.dao;

import ua.artcode.model.User;
import ua.artcode.model.UserPost;

import java.util.List;

/**
 * Created by root on 04.06.2015.
 */
public interface PostDao {
    UserPost createPost(User user, UserPost post);
    UserPost getById(long id);
    List<UserPost> userPosts(long id);
}
