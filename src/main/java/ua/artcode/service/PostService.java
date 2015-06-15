package ua.artcode.service;

import ua.artcode.model.User;
import ua.artcode.model.UserPost;

import java.util.List;

/**
 * Created by serhii on 30.05.15.
 */
public interface PostService {

    UserPost createPost(User user, UserPost userPost);
    List<UserPost> userPosts(long userId);
    UserPost getById(long postId);
    boolean delete(long userId, long postId); //TODO implement this method

}
