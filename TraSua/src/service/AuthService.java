/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Minhphat
 */
import model.User;
import repository.UserRepository;

public class AuthService {
    private final UserRepository userRepo = new UserRepository();
    private User currentUser;

    public boolean signUp(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }
        if (userRepo.exists(username)) {
            return false;
        }
        userRepo.saveUser(new User(username, password));
        return true;
    }

    public boolean login(String username, String password) {
        User user = userRepo.findUser(username, password);
        if (user != null) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
