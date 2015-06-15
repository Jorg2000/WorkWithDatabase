package ua.artcode.service;

import ua.artcode.dao.PostDao;
import ua.artcode.dao.UserDao;
import ua.artcode.model.User;
import ua.artcode.model.UserPost;

import java.util.List;

/**
 * Created by root on 03.06.2015.
 */
public class PostServiceImpl implements PostService {

    private PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }


    public UserPost createPost(User user, UserPost userPost) {
        postDao.createPost(user,userPost);
        return userPost;
    }

    public List<UserPost> userPosts(long userId) {
        return postDao.userPosts(userId);
    }

    public UserPost getById(long postId) {
        return postDao.getById(postId);
    }

    public boolean delete(long userId, long postId) {
        return false;
    }
}
