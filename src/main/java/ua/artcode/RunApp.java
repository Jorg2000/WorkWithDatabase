package ua.artcode;

import ua.artcode.dao.*;
import ua.artcode.service.*;
import ua.artcode.storage.AppDataContainer;
import ua.artcode.view.ConsoleUserView;
import ua.artcode.view.UserView;

/**
 * Created by serhii on 30.05.15.
 */
public class RunApp {

    public static void main(String[] args) {
        AppDataContainer container = new AppDataContainer();
        //UserDao userDao = new SimpleStorageUserDao(container); // ТУт ???
        //UserDao userDao = new JDBCUserDao();
        UserDao userDao = new HibernateUserDAO();
        PostDao postDao = new JDBCPostDAO();


        UserService userService = new UserServiceImpl(userDao);
        PostService postService = new PostServiceImpl(postDao);
        SystemState systemState = new SystemStateImpl();

        UserView userView = new ConsoleUserView(userService, postService, systemState);

        userView.start();
    }
}
