package com.example.employeepayroll.service;

import com.example.employeepayroll.dto.EmployeeDTO;
import com.example.employeepayroll.exception.EmployeeNotFoundException;
import com.example.employeepayroll.model.Employee;
import com.example.employeepayroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // ✅ Add Employee
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding new employee: {}", employeeDTO.getName());
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    // ✅ Get All Employees
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees...");
        return employeeRepository.findAll();
    }

    // ✅ Get Employee by ID (Throws Exception if Not Found)
    public Employee getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    // ✅ Update Employee (Throws Exception if Not Found)
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    // ✅ Delete Employee (Throws Exception if Not Found)
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
