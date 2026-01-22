package com.example.lab3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.lab3.model.Student;
import com.example.lab3.service.StudentService;

// http://localhost:8080/students

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentApiController {

    @Autowired
    private StudentService service;
// http://localhost:8080/students/add
    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping("/json-all")
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        return service.findByName(name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteStudent(id);
    }
}
