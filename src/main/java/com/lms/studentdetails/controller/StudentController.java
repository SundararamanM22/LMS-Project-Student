package com.lms.studentdetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.studentdetails.entity.Student;
import com.lms.studentdetails.exceptions.AgeUnder18Exception;
import com.lms.studentdetails.exceptions.UpdateResultTableException;
import com.lms.studentdetails.service.StudentService;

@RestController
@RequestMapping(value = "/studentDetails")
public class StudentController {
	@Autowired
	StudentService sSer;
	
	@PostMapping(value = "/setStudent")
	public String setStudent(@RequestBody Student student) throws AgeUnder18Exception, Exception {
		return sSer.setStudent(student);
	}
	
	@PostMapping(value = "/setStudents")
	public String setStudents(@RequestBody List<Student> students) throws AgeUnder18Exception, Exception {
		return sSer.setStudents(students);
	}
	
	@GetMapping(value = "/getStudent")
	public Student getStudent(@RequestParam int id) {
		return sSer.getStudent(id);
	}
	
	@GetMapping(value = "/getAllStudents")
	public List<Student> getAllStudents() {
		return sSer.getAllStudents();
	}
	
	@PutMapping(value = "/updateStudentNameByRollNumber")
	public String updateStudentNameByRollNumber(@RequestParam int rollNumber, @RequestParam String name) throws UpdateResultTableException, Exception {
		return sSer.updateStudentNameByRollNumber(rollNumber, name);
	}
	
	@DeleteMapping(value = "/deleteStudent")
	public String deleteStudent(@RequestParam int id) {
		return sSer.deleteStudent(id);
	}
	
	@GetMapping(value = "/getStudentDetailsByRollNumber")
	public Student getStudentDetailsByRollNumber(int rollNumber) {
		return sSer.getStudentDetailsByRollNumber(rollNumber);
	}
}
