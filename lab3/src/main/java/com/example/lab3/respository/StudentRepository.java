package com.example.lab3.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab3.model.Student;
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByNameContainingIgnoreCase(String name);
}
