package com.example.employeepayroll.service;

import com.example.employeepayroll.dto.EmployeeDTO;
import com.example.employeepayroll.model.Employee;
import com.example.employeepayroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j // ✅ Enables logging
@Service
@RequiredArgsConstructor // ✅ Generates constructor for final fields
public class EmployeeService {

    private final EmployeeRepository employeeRepository; // ✅ No need for @Autowired

    // ✅ Add Employee to Database
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding new employee: {}", employeeDTO.getName());
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee added successfully with ID: {}", savedEmployee.getId());
        return savedEmployee;
    }

    // ✅ Get All Employees
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees from database...");
        return employeeRepository.findAll();
    }

    // ✅ Get Employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeRepository.findById(id);
    }

    // ✅ Update Employee
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            Employee updatedEmployee = employeeRepository.save(employee);
            log.info("Employee updated successfully: {}", updatedEmployee);
            return updatedEmployee;
        }
        log.warn("Employee with ID {} not found", id);
        return null;
    }

    // ✅ Delete Employee
    public boolean deleteEmployee(Long id) {
        log.info("Attempting to delete employee with ID: {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            log.info("Employee with ID {} deleted successfully", id);
            return true;
        }
        log.warn("Employee with ID {} not found, deletion failed", id);
        return false;
    }
}
