package com.example.employee_search_api.repository;

import com.example.employee_search_api.document.EmployeeDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeSearchRepository
        extends ElasticsearchRepository<EmployeeDocument, Long>,
        EmployeeSearchRepositoryCustom {
}