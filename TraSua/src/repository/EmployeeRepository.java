/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Minhphat
 */
import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private static final List<Employee> employees
            = new ArrayList<>();

    // =====================================
    // ADD
    // =====================================
    public void addEmployee(Employee employee) {

        employees.add(employee);
    }

    // =====================================
    // GET ALL
    // =====================================
    public List<Employee> getAllEmployees() {

        return employees;
    }

    // =====================================
    // FIND BY ID
    // =====================================
    public Employee findById(String id) {

        for (Employee e : employees) {

            if (e.getId().equalsIgnoreCase(id)) {

                return e;
            }
        }

        return null;
    }

    // =====================================
    // DELETE
    // =====================================
    public boolean deleteEmployee(String id) {

        Employee e = findById(id);

        if (e != null) {

            employees.remove(e);

            return true;
        }

        return false;
    }

    // =====================================
    // UPDATE
    // =====================================
    public boolean updateEmployee(
            String id,
            String newName,
            String newRole,
            double newSalary
    ) {

        Employee e = findById(id);

        if (e != null) {

            e.setName(newName);
            e.setRole(newRole);
            e.setSalary(newSalary);

            return true;
        }

        return false;
    }
}
