package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int save(Student s){
        System.out.println("Student saved");
        String sql = "insert into students (roll_number,name,marks,branch) values (?,?,?,?)";
        return jdbc.update(sql,s.getRollNumber(),s.getName(),s.getMarks(),s.getBranch());
    }

    public List<Student> findAll() {

        String sql ="select * from students";

        return jdbc.query(sql, ((rs, rowNum) -> {  //Implementing mapRow() of RowMapper function interface
            Student s = new Student();
            s.setRollNumber(rs.getInt("roll_number"));
            s.setName(rs.getString("name"));
            s.setMarks(rs.getInt("marks"));
            s.setBranch(rs.getString("branch"));
            return s;
        }));
    }
}
