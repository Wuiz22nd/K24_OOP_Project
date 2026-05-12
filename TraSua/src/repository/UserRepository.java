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

    private static final String FILE_NAME = "users.txt";

    // =========================================
    // SAVE USER
    // =========================================
    public void saveUser(User user) {

        try (

                BufferedWriter bw =
                        new BufferedWriter(
                                new FileWriter(
                                        FILE_NAME,
                                        true
                                )
                        )

        ) {

            bw.write(
                    user.getUsername()
                    + ","
                    + user.getPassword()
            );

            bw.newLine();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // =========================================
    // CHECK EXIST
    // =========================================
    public boolean exists(String username) {

        List<User> users = getAllUsers();

        for (User user : users) {

            if (
                    user.getUsername()
                            .equalsIgnoreCase(username)
            ) {

                return true;
            }
        }

        return false;
    }

    // =========================================
    // GET ALL USERS
    // =========================================
    public List<User> getAllUsers() {

        List<User> users =
                new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            return users;
        }

        try (

                BufferedReader br =
                        new BufferedReader(
                                new FileReader(file)
                        )

        ) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data =
                        line.split(",");

                if (data.length == 2) {

                    User user =
                            new User(
                                    data[0],
                                    data[1]
                            );

                    users.add(user);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return users;
    }

    // =========================================
    // LOGIN
    // =========================================
    public User findUser(
            String username,
            String password
    ) {

        List<User> users = getAllUsers();

        for (User user : users) {

            boolean correctUsername =
                    user.getUsername()
                            .equals(username);

            boolean correctPassword =
                    user.getPassword()
                            .equals(password);

            if (
                    correctUsername
                    && correctPassword
            ) {

                return user;
            }
        }

        return null;
    }
}