package com.lms.studentdetails.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lms.studentdetails.entity.Student;
import com.lms.studentdetails.repository.StudentRepository;

@Repository
public class StudentDao {
	@Autowired
	StudentRepository sRepo;
	
	public String setStudent(Student student) {
		sRepo.save(student);
		return "Saved successfully";
	}
	
	public String setStudents(List<Student> students) {
		sRepo.saveAll(students);
		return "Saved all successfully";
	}
	
	public Student getStudent(int id) {
		return sRepo.findById(id).get();
	}
	
	public List<Student> getAllStudents() {
		return sRepo.findAll();
	}
	
	public String deleteStudent(int id) {
		sRepo.deleteById(id);
		return "Deleted successfully";
	}
}
