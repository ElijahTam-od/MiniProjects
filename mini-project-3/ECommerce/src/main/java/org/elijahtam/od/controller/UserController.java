package org.elijahtam.od.controller;

import org.elijahtam.od.entities.User;
import org.elijahtam.od.view.UserView;

/**
 * The UserController class manages operations related to the User entity,
 * such as displaying user information using the associated UserView.
 */
public class UserController {
    private final User user;
    private final UserView userView;

    /**
     * Constructs a UserController object with the specified User and UserView.
     *
     * @param user the User object representing the user
     * @param userView the UserView object for displaying user information
     */
    public UserController(User user, UserView userView) {
        this.user = user;
        this.userView = userView;
    }

    /**
     * Displays the user information using the associated UserView.
     */
    public void displayUser() {
        userView.displayUser(user);
    }

    /**
     * Retrieves the User object managed by this UserController.
     *
     * @return the User object associated with this UserController
     */
    public User getUser() {
        return user;
    }
}

