package com.example.employee_search_api.repository;

import com.example.employee_search_api.document.EmployeeDocument;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeSearchRepositoryImpl
        implements EmployeeSearchRepositoryCustom {

    private final ElasticsearchTemplate elasticsearchTemplate;

    public EmployeeSearchRepositoryImpl(
            ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public List<EmployeeDocument> searchEmployees(String keyword) {

        String jsonQuery = """
            {
              "multi_match": {
                "query": "%s",
                "type": "bool_prefix",
                "fields": [
                  "name",
                  "email",
                  "department",
                  "skills"
                ]
              }
            }
            """.formatted(keyword);

        Query query = new StringQuery(jsonQuery);

        SearchHits<EmployeeDocument> searchHits =
                elasticsearchTemplate.search(
                        query,
                        EmployeeDocument.class
                );

        return searchHits.getSearchHits()
                .stream()
                .map(hit -> hit.getContent())
                .toList();
    }
}