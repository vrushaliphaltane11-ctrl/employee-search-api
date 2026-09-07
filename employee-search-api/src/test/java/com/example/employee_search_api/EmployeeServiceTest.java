package com.example.employee_search_api;

import com.example.employee_search_api.entity.Employee;
import com.example.employee_search_api.repository.EmployeeRepository;
import com.example.employee_search_api.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldCreateEmployee() {

        Employee employee =
                new Employee("John", "john@gmail.com", "IT", "Java");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("john@gmail.com", result.getEmail());

        verify(employeeRepository).save(employee);
    }

    @Test
    void shouldGetAllEmployees() {

        Employee employee1 =
                new Employee("John", "john@gmail.com", "IT", "Java");

        Employee employee2 =
                new Employee("Alice", "alice@gmail.com", "HR", "Recruitment");

        when(employeeRepository.findAll())
                .thenReturn(List.of(employee1, employee2));

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Alice", result.get(1).getName());

        verify(employeeRepository).findAll();
    }

    @Test
    void shouldGetEmployeeById() {

        Employee employee =
                new Employee("John", "john@gmail.com", "IT", "Java");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployee(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("john@gmail.com", result.getEmail());

        verify(employeeRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {

        when(employeeRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> employeeService.getEmployee(99L)
        );

        assertEquals("Employee not found", exception.getMessage());

        verify(employeeRepository).findById(99L);
    }

    @Test
    void shouldDeleteEmployee() {

        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L);
    }
}