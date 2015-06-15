package ua.artcode.view;

import ua.artcode.exception.LoginException;
import ua.artcode.exception.RegistrarException;
import ua.artcode.exception.UnknownUserIdException;
import ua.artcode.model.User;
import ua.artcode.model.UserPost;
import ua.artcode.service.PostService;
import ua.artcode.service.SystemState;
import ua.artcode.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by serhii on 30.05.15.
 */
public class ConsoleUserView implements UserView {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private UserService userService;
    private PostService postService;
    private SystemState systemState;
  //  private User currentUser;


    public ConsoleUserView(UserService userService, PostService postService, SystemState systemState) {
        this.userService = userService;
        this.systemState = systemState;
        this.postService = postService;
    }

    public void start() {
        while (true){
            showMainMenu();
            try {
                int choice = Integer.parseInt(bufferedReader.readLine().trim());

                if(choice == 7){
                    break;
                }

                doChoice(choice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void doChoice(int choice){
        switch (choice){
            case 0 : {
                postMenu();
                break;
            }
            case 1 : {
                registerMenu();
                break;
            }
            case 2 : {
                loginMenu();
                break;
            }
            case 3 : {
                showUsers();
                break;
            }
            case 4 : {
                deleteMenu();
                break;
            }
            case 5 : {
                viewPostByIdMenu();
                break;
            }
            case 6 : {
                viewPostByUserIdMenu();
                break;
            }


           default:
                System.out.println("wrong choice");
                break;

        }
    }

    private void viewPostByUserIdMenu() {
        List<UserPost> userPosts = null;
        try {
            System.out.println("Enter user ID");
            int userId = Integer.parseInt(bufferedReader.readLine().trim());
            userPosts = postService.userPosts(userId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (UserPost userPost : userPosts) {
            System.out.println(userPost.toString() + "\n");
        }

    }

    private void viewPostByIdMenu() {
        UserPost post = null;
        try {
            System.out.println("Enter post ID");
            int postId = Integer.parseInt(bufferedReader.readLine().trim());
           post =  postService.getById(postId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(post.toString());

    }

    public void showUsers(){
        List<User> userList = userService.getAll();
        System.out.println("USER LIST");
        for(User u : userList){
            System.out.println("\t" + u);
        }
    }


    private void showMainMenu(){
        User currentUser = systemState.getCurrentUser();

        if (currentUser != null) {
            System.out.println("Current user is " + currentUser.getFullName());
            System.out.println("0. Write new post");
        }

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Show all users");
        System.out.println("4. Delete user by ID");
        System.out.println("5. Show post by ID");
        System.out.println("6. Show all user's posts by userID");
        System.out.println("7. Exit");

    }

    private void deleteMenu(){
        System.out.println("Input user ID");
        User user = null;
        try {
            long id = Integer.parseInt(bufferedReader.readLine().trim());
            user = userService.delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnknownUserIdException e) {
            e.printStackTrace();
        }
        System.out.println("User" + user + "Successfully deleted");
    }

    private void loginMenu(){
        try {
            System.out.println("Input email");
            String email = bufferedReader.readLine().trim();
            System.out.println("Input pass");
            String pass = bufferedReader.readLine().trim();
            User user = userService.login(email,pass);
            System.out.println(user  + " in system");
            systemState.setCurrentUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            System.err.println(e.getMessage());
        }
    }

    private void postMenu(){
        try {
            System.out.println("Input title");
            String title = bufferedReader.readLine().trim();
            System.out.print(("Input Post"));
            String body = bufferedReader.readLine().trim();
            UserPost userPost = new UserPost(title,body,systemState.getCurrentUser(),new Date());
            postService.createPost(systemState.getCurrentUser(),userPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerMenu(){
        try {
            System.out.println("Input email");
            String email = bufferedReader.readLine().trim();
            System.out.println("Input fullname");
            String fullname = bufferedReader.readLine().trim();
            System.out.println("Input pass");
            String pass = bufferedReader.readLine().trim();

            userService.register(email,fullname,pass);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (RegistrarException e) {
            System.err.println(e.getMessage());
        }
    }

}
