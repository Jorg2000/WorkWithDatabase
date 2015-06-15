package ua.artcode.service;

import ua.artcode.model.User;

/**
 * Created by root on 03.06.2015.
 */
public interface SystemState {
    public void setCurrentUser(User user);
    public User getCurrentUser();

}
