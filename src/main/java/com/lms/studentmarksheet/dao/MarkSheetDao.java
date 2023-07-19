package com.lms.studentmarksheet.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lms.studentmarksheet.entity.MarkSheet;
import com.lms.studentmarksheet.repository.MarkSheetRepository;

@Repository
public class MarkSheetDao {
	@Autowired
	MarkSheetRepository mRepo;
	
	public String setStudentMarksheet(MarkSheet marksheet) {
		mRepo.save(marksheet);
		return "Saved successfully";
	}
	
	public String setStudentsMarksheets(List<MarkSheet> marksheets) {
		mRepo.saveAll(marksheets);
		return "Saved all successfully";
	}
	
	public MarkSheet getStudentMarksheet(int id) {
		return mRepo.findById(id).get();
	}
	
	public List<MarkSheet> getStudentsMarksheets() {
		return mRepo.findAll();
	}
}
