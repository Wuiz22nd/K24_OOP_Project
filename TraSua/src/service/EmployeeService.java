/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Minhphat
 */
import model.Employee;
import repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository repo
            = new EmployeeRepository();

    // =====================================
    // ADD
    // =====================================
    public void addEmployee(
            String id,
            String name,
            String role,
            double salary
    ) {

        Employee employee =
                new Employee(
                        id,
                        name,
                        role,
                        salary
                );

        repo.addEmployee(employee);
    }

    // =====================================
    // GET ALL
    // =====================================
    public List<Employee> getAllEmployees() {

        return repo.getAllEmployees();
    }

    // =====================================
    // DELETE
    // =====================================
    public boolean deleteEmployee(String id) {

        return repo.deleteEmployee(id);
    }

    // =====================================
    // UPDATE
    // =====================================
    public boolean updateEmployee(
            String id,
            String name,
            String role,
            double salary
    ) {

        return repo.updateEmployee(
                id,
                name,
                role,
                salary
        );
    }

    // =====================================
    // FIND
    // =====================================
    public Employee findEmployee(String id) {

        return repo.findById(id);
    }
}
