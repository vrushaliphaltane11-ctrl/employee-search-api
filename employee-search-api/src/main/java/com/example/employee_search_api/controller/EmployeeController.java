package com.example.employee_search_api.controller;

import com.example.employee_search_api.entity.Employee;
import com.example.employee_search_api.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import com.example.employee_search_api.document.EmployeeDocument;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/search")
    public List<EmployeeDocument> searchEmployees(
            @RequestParam String keyword) {
        return employeeService.searchEmployees(keyword);
    }
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}