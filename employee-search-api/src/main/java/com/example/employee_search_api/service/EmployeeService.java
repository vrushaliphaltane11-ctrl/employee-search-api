package com.example.employee_search_api.service;

import com.example.employee_search_api.document.EmployeeDocument;
import com.example.employee_search_api.entity.Employee;
import com.example.employee_search_api.repository.EmployeeRepository;
import com.example.employee_search_api.repository.EmployeeSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeSearchRepository employeeSearchRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeSearchRepository employeeSearchRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeSearchRepository = employeeSearchRepository;
    }

    public Employee createEmployee(Employee employee) {

        // Save employee in MySQL
        Employee savedEmployee = employeeRepository.save(employee);

        // Create Elasticsearch document
        EmployeeDocument document = new EmployeeDocument(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartment(),
                savedEmployee.getSkills()
        );

        // Save document to Elasticsearch
        employeeSearchRepository.save(document);

        return savedEmployee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {

        // Delete from MySQL
        employeeRepository.deleteById(id);

        // Delete from Elasticsearch
        employeeSearchRepository.deleteById(id);
    }

    public List<EmployeeDocument> searchEmployees(String keyword) {
        return employeeSearchRepository.searchEmployees(keyword);
    }
}