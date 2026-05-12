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

    private final UserRepository userRepository;

    // USER ĐANG ĐĂNG NHẬP
    private User currentUser;

    public AuthService() {
        userRepository = new UserRepository();
    }

    // =====================================================
    // LOGIN
    // =====================================================
    public boolean login(String username, String password) {

        User user = userRepository.findUser(username, password);

        if (user != null) {

            currentUser = user;

            return true;
        }

        return false;
    }

    // =====================================================
    // REGISTER
    // =====================================================
    public boolean signUp(String username, String password) {

        // Username phải có @gmail.com
        if (!username.matches("^[a-zA-Z0-9]{6,}@gmail\\.com$")) {
            return false;
        }

        // Password ít nhất 8 ký tự/chữ/số
        if (!password.matches("^[a-zA-Z0-9]{8,}$")) {
            return false;
        }

        // Check tồn tại
        if (userRepository.exists(username)) {
            return false;
        }

        User user = new User(username, password);

        userRepository.saveUser(user);

        return true;
    }

    // =====================================================
    // GET CURRENT USER
    // =====================================================
    public User getCurrentUser() {
        return currentUser;
    }

    // =====================================================
    // LOGOUT
    // =====================================================
    public void logout() {
        currentUser = null;
    }
}