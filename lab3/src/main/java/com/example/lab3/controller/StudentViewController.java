package com.example.lab3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.lab3.model.Student;
import com.example.lab3.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentViewController {

    @Autowired
    private StudentService service;

    // 🔹 LIST + SEARCH
    @GetMapping
    public String list(
            @RequestParam(required = false) String name,
            Model model) {

        List<Student> students =
                (name == null || name.isEmpty())
                        ? service.getAll()
                        : service.findByName(name);

        model.addAttribute("students", students);
        model.addAttribute("keyword", name);
        return "list";
    }

    // 🔹 FORM ADD
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    // 🔹 FORM EDIT
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "form";
    }

    // 🔹 SAVE (ADD + EDIT)
    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        service.addStudent(student);
        return "redirect:/students";
    }

    // 🔹 DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteStudent(id);
        return "redirect:/students";
    }

    // 🔹 DETAIL
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "detail";
    }
}
