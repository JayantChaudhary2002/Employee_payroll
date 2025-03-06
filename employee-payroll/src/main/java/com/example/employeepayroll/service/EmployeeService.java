package com.example.employeepayroll.service;

import com.example.employeepayroll.dto.EmployeeDTO;
import com.example.employeepayroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // To generate unique IDs

    // Add Employee
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(employee);
        return employee;
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    // Get Employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    // Update Employee
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = getEmployeeById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return employee;
        }
        return null;
    }

    // Delete Employee
    public boolean deleteEmployee(Long id) {
        return employeeList.removeIf(employee -> employee.getId().equals(id));
    }
}
