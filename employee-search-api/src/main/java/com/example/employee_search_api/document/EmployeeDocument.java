package com.example.employee_search_api.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "employees")
public record EmployeeDocument(
        @Id
        Long id,

        String name,

        String email,

        String department,

        String skills
) {
}