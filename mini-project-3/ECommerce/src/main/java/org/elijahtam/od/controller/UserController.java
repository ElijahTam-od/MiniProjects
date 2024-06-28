package org.elijahtam.od.controller;

import org.elijahtam.od.entities.User;
import org.elijahtam.od.view.UserView;

/**
 * The {@code UserController} class represents the controller for the user.
 */
public class UserController {
    private final User user;
    private final UserView userView;

    public UserController(User user, UserView userView) {
        this.user = user;
        this.userView = userView;
    }

    public void displayUser() {
        userView.displayUser(user);
    }

    public User getUser() {
        return user;
    }
}
