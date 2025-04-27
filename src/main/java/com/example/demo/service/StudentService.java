package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student addStudent(Student student){
        return studentRepo.save(student);
    }

    public Optional<Student> getStudent(int rollNumber) {
        return studentRepo.findById(rollNumber);
    }

    public boolean removeStudent(int rollNumber){
        if(studentRepo.existsById(rollNumber)){
            studentRepo.deleteById(rollNumber);
            return true;
        }
        else{
            return false;
        }
    }

    public Student updateStudent(Student student){
        return studentRepo.save(student);
    }

    public List<Student> getStudentsByBranch(String branch) {
        return studentRepo.getByBranch(branch);
    }

    public List<String> getBranches(){
        return studentRepo.getBranches();
    }
}
