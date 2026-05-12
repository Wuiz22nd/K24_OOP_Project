/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class User {

    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter username
    public String getUsername() {
        return username;
    }

    // Setter username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter password
    public String getPassword() {
        return password;
    }

    // Setter password
    public void setPassword(String password) {
        this.password = password;
    }
}