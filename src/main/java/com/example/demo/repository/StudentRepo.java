package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepo extends JpaRepository<Student, UUID> {
    //some common column related method that JPA implicitly provides
    List<Student> getByBranch(String branch);

    //some advance operations this is JSQL query
    @Query("SELECT DISTINCT s.branch FROM Student s")
    List<String> getBranches();
}
