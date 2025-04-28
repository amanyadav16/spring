package com.example.demo.controller;

import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Student> getStudent(@PathVariable UUID rollNumber) {
        return ResponseEntity.ok(studentService.getStudent(rollNumber).orElseThrow(() -> new StudentNotFoundException("Student not exist.")));
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
    public ResponseEntity<String> deleteStudent(@PathVariable UUID rollNumber) {
        boolean isDeleted = studentService.removeStudent(rollNumber);
        if (isDeleted)
            return ResponseEntity.ok("Student deleted successfully!");
        else
            throw new StudentNotFoundException("Student not exist.");
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
