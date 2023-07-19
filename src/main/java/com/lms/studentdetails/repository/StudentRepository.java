package com.lms.studentdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.studentdetails.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
