package com.example.employee_search_api.repository;

import com.example.employee_search_api.document.EmployeeDocument;

import java.util.List;

public interface EmployeeSearchRepositoryCustom {

    List<EmployeeDocument> searchEmployees(String keyword);
}