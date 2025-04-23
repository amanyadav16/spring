package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(DemoApplication.class, args);
		StudentService studentService = context.getBean(StudentService.class);
		Student s = context.getBean(Student.class);
		s.setRollNumber(5);
		s.setName("Neha");
		s.setMarks(95);
		s.setBranch("CS");
		List<Student> sList = studentService.getAllStudents();

		for(Student student:sList){
			System.out.println(student);
		}
//		int rowsEffected=studentService.saveStudent(s);
//		System.out.println(rowsEffected+" rows effected");
	}
}
