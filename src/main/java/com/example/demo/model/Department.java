package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

// Department Details
@Entity
public class Department {

    @Id
    private int deptId; 

    private String deptName; 

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employeeList;

    // Default constructor
    public Department() {
        this.employeeList = new ArrayList<>();
    }

    // Parameterized constructor
    public Department(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.employeeList = new ArrayList<>();
    }

    // Getters and Setters
    public int getDeptId() {
        return this.deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
    }
}

