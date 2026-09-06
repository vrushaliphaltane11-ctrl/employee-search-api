package com.example.employee_search_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String department;
    private String skills;

    public Employee()
    {
    }
    public Employee(String name,String email,String department,String Skills) {
        this.name = name;
        this.email=email;
        this.department=department;
        this.skills=skills;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name)
    {
      this.name=name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getDepartment()
    {
        return department;
    }
    public void setDepartment(String department)
    {
        this.department=department;
    }
    public String getSkills()
    {
        return skills;
    }
    public void setSkills(String skills)
    {
        this.skills=skills;
    }

}
