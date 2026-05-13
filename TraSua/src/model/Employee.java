/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class Employee {

    private String id;
    private String name;
    private String role;
    private double salary;

    // =====================================
    // CONSTRUCTOR
    // =====================================
    public Employee(
            String id,
            String name,
            String role,
            double salary
    ) {

        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    // =====================================
    // GETTER & SETTER
    // =====================================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // =====================================
    // DISPLAY
    // =====================================
    @Override
    public String toString() {

        return id
                + " - "
                + name
                + " - "
                + role
                + " - "
                + salary;
    }
}
