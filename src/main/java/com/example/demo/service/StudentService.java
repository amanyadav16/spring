package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Cacheable(value = "allStudents", key = "'all'")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @CacheEvict(value = "allStudents", key = "'all'")
    public Student addStudent(Student student){
        return studentRepo.save(student);
    }

    @Cacheable(value = "students" , key = "#rollNumber")
    public Optional<Student> getStudent(UUID rollNumber) {
        return studentRepo.findById(rollNumber);
    }

    @CacheEvict(value = "students", key = "#rollNumber")
    public boolean removeStudent(UUID rollNumber){
        if(studentRepo.existsById(rollNumber)){
            studentRepo.deleteById(rollNumber);
            return true;
        }
        else{
            return false;
        }
    }

    @CachePut(value = "students", key = "#student.rollNumber")
    public Student updateStudent(Student student){
        return studentRepo.save(student);
    }

    @Cacheable(value = "branches" , key = "#branch")
    public List<Student> getStudentsByBranch(String branch) {
        return studentRepo.getByBranch(branch);
    }

    public List<String> getBranches(){
        return studentRepo.getBranches();
    }
}
