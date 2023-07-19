package com.lms.studentdetails.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lms.studentdetails.dao.StudentDao;
import com.lms.studentdetails.entity.Student;
import com.lms.studentdetails.exceptions.AgeUnder18Exception;
import com.lms.studentdetails.exceptions.UpdateResultTableException;

@Service
public class StudentService {
	@Autowired
	StudentDao sDao;
	
	@Autowired
	RestTemplate rt;

	public String setStudent(Student student) throws AgeUnder18Exception, Exception {
		if (student.getAge() < 18) {
			throw new AgeUnder18Exception("The student must be over 18 years old");
		} else {
			return sDao.setStudent(student);
		}
	}

	public String setStudents(List<Student> students) throws AgeUnder18Exception, Exception {
		for(Student student : students) {
			if (student.getAge() < 18) {
				//students.remove(student);
				throw new AgeUnder18Exception("The student must be over 18 years old");
			}
		}
		return sDao.setStudents(students);
	}

	public Student getStudent(int id) {
		return sDao.getStudent(id);
	}

	public List<Student> getAllStudents() {
		return sDao.getAllStudents();
	}

	public String updateStudentNameByRollNumber(int rollNumber, String name) throws UpdateResultTableException, Exception {
		List<Student> students = sDao.getAllStudents();
		for(Student student : students) {
			if(student.getRollNumber() == rollNumber) {
				student.setName(name);
			}
			sDao.setStudent(student);
		}
		
		String url = "http://localhost:8082/studentResult/updateStudentNameByRollNumber";
		String resp = rt.exchange(url + "?rollNumber=" + rollNumber + "&name=" + name, HttpMethod.PUT, null, String.class).getBody();
		if(resp.equals("Updated successfully")) {
			return "Updated successfully";
		} else {
			throw new UpdateResultTableException("Result table not updated");
		}		
	}

	public String deleteStudent(int id) {
		return sDao.deleteStudent(id);
	}
	
	public Student getStudentDetailsByRollNumber(int rollNumber) {
		List<Student> details = sDao.getAllStudents();		
		return details.stream().filter(x -> x.getRollNumber() == rollNumber).collect(Collectors.toList()).get(0);
	}
}
