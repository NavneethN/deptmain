
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Department;
import com.example.demo.repo.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/depart")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        try {
            Department _department = departmentRepository.save(new Department(department.getDeptId(), department.getDeptName()));
            return new ResponseEntity<>(_department, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/depart")
    public ResponseEntity<List<Department>> getDepartments() {
        try {
            List<Department> departments = departmentRepository.findAll();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/depart/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
        Optional<Department> departmentData = departmentRepository.findById(id);
        return departmentData.map(department -> new ResponseEntity<>(department, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/depart/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int id, @RequestBody Department department) {
        Optional<Department> departmentData = departmentRepository.findById(id);
        return departmentData.map(existingDepartment -> {
            existingDepartment.setDeptName(department.getDeptName());
            return new ResponseEntity<>(departmentRepository.save(existingDepartment), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/depart/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable int id) {
        try {
            departmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

