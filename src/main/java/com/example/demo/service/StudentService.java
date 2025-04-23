package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepo studentRepo;

    public StudentRepo getStudentRepo() {
        return studentRepo;
    }

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public int saveStudent(Student s){
        return studentRepo.save(s);
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
}
