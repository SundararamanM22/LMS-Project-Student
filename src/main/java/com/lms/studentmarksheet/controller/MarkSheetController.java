package com.lms.studentmarksheet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.studentmarksheet.entity.MarkSheet;
import com.lms.studentmarksheet.exceptions.UpdateResultTableException;
import com.lms.studentmarksheet.service.MarkSheetService;

@RestController
@RequestMapping(value = "/studentMarksheet")
public class MarkSheetController {
	@Autowired
	MarkSheetService mSer;
	
	@PostMapping(value = "/setStudentMarksheet")
	public String setStudentMarksheet(@RequestBody MarkSheet marksheet) {
		return mSer.setStudentMarksheet(marksheet);
	}
	
	@PostMapping(value = "/setStudentsMarksheets")
	public String setStudentsMarksheets(@RequestBody List<MarkSheet> marksheets) {
		return mSer.setStudentsMarksheets(marksheets);
	}
	
	@GetMapping(value = "/getStudentMarksheet")
	public MarkSheet getStudentMarksheet(@RequestParam int id) {
		return mSer.getStudentMarksheet(id);
	}
	
	@GetMapping(value = "/getStudentsMarksheets")
	public List<MarkSheet> getStudentsMarksheets() {
		return mSer.getStudentsMarksheets();
	}
	
	@GetMapping(value = "/getStudentMarksheetByRollNumber")
	public MarkSheet getStudentMarksheetByRollNumber(@RequestParam int rollNumber) {
		return mSer.getStudentMarksheetByRollNumber(rollNumber);
	}
	
	@PutMapping(value = "/updateSem1TheoryByRollNumber")
	public String updateSem1TheoryByRollNumber(@RequestParam int rollNumber, @RequestParam int sem1Theory)  throws UpdateResultTableException, Exception  {
		return mSer.updateSem1TheoryByRollNumber(rollNumber,sem1Theory);
	}
	
	@PutMapping(value = "/updateSem1PracticalsByRollNumber")
	public String updateSem1PracticalsByRollNumber(@RequestParam int rollNumber, @RequestParam int sem1Practicals)  throws UpdateResultTableException, Exception  {
		return mSer.updateSem1PracticalsByRollNumber(rollNumber,sem1Practicals);
	}
	
	@PutMapping(value = "/updateSem2TheoryByRollNumber")
	public String updateSem2TheoryByRollNumber(@RequestParam int rollNumber, @RequestParam int sem2Theory)  throws UpdateResultTableException, Exception  {
		return mSer.updateSem2TheoryByRollNumber(rollNumber,sem2Theory);
	}
	
	@PutMapping(value = "/updateSem2PracticalsByRollNumber")
	public String updateSem2PracticalsByRollNumber(@RequestParam int rollNumber, @RequestParam int sem2Practicals)  throws UpdateResultTableException, Exception  {
		return mSer.updateSem2PracticalsByRollNumber(rollNumber,sem2Practicals);
	}
}
