/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Minhphat
 */
import model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String FILE = "users.txt";

    public boolean exists(String username) {
        for (User u : getAllUsers()) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void saveUser(User user) {
        if (exists(user.getUsername())) {
            System.out.println("Username da ton tai!");
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(user.getUsername() + "," + user.getPassword());
            System.out.println("Dang ky thanh cong!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.add(new User(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findUser(String username, String password) {
        for (User u : getAllUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}