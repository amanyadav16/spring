package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//@CrossOrigin
@Getter
@Setter
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{rollNumber}")
    public ResponseEntity<Student> getStudent(@PathVariable int rollNumber) {
        return ResponseEntity.ok(studentService.getStudent(rollNumber).orElse(new Student()));
    }

    @PostMapping("")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) { //converts object to json body
        return ResponseEntity.status(201).body(studentService.addStudent(student));
    }

    @PutMapping("")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/{rollNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable int rollNumber) {
        boolean isDeleted = studentService.removeStudent(rollNumber);
        return isDeleted ?
                ResponseEntity.ok("Student deleted successfully!") :
                ResponseEntity.status(404).body("Student not found.");
    }

    //other operations
    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<Student>> getStudentByBranch(@PathVariable String branch) {
        return ResponseEntity.ok(studentService.getStudentsByBranch(branch));
    }

    @GetMapping("/branches")
    public ResponseEntity<List<String>> getBranches() {
        return ResponseEntity.ok(studentService.getBranches());
    }
}
