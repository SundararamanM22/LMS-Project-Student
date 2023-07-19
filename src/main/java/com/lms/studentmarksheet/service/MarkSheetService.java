package com.lms.studentmarksheet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lms.studentmarksheet.dao.MarkSheetDao;
import com.lms.studentmarksheet.entity.MarkSheet;
import com.lms.studentmarksheet.exceptions.UpdateResultTableException;

@Service
public class MarkSheetService {
	@Autowired
	MarkSheetDao mDao;

	@Autowired
	RestTemplate rt;

	public String setStudentMarksheet(MarkSheet marksheet) {
		marksheet.setSem1Total(marksheet.getSem1Theory() + marksheet.getSem1Practicals());
		marksheet.setSem2Total(marksheet.getSem2Theory() + marksheet.getSem2Practicals());
		return mDao.setStudentMarksheet(marksheet);
	}

	public String setStudentsMarksheets(List<MarkSheet> marksheets) {
		for (MarkSheet marksheet : marksheets) {
			marksheet.setSem1Total(marksheet.getSem1Theory() + marksheet.getSem1Practicals());
			marksheet.setSem2Total(marksheet.getSem2Theory() + marksheet.getSem2Practicals());
		}
		return mDao.setStudentsMarksheets(marksheets);
	}

	public MarkSheet getStudentMarksheet(int id) {
		return mDao.getStudentMarksheet(id);
	}

	public List<MarkSheet> getStudentsMarksheets() {
		return mDao.getStudentsMarksheets();
	}

	public MarkSheet getStudentMarksheetByRollNumber(int rollNumber) {
		List<MarkSheet> marksheets = mDao.getStudentsMarksheets();
		return marksheets.stream().filter(x -> x.getRollNumber() == rollNumber).collect(Collectors.toList()).get(0);
	}
	
	public String updateResultTable(MarkSheet marksheet) {		
		String url = "http://localhost:8082/studentResult/updateStudentMarksByRollNumber?rollNumber="
				+ marksheet.getRollNumber() + "&sem1Total=" + marksheet.getSem1Total() + "&sem2Total=" + marksheet.getSem2Total();
		String resp = rt.exchange(url, HttpMethod.PUT, null, String.class).getBody();
		return resp;
	}
	
	public String verifyUpdation(MarkSheet marksheet) throws UpdateResultTableException, Exception {
		setStudentMarksheet(marksheet);	
		if (updateResultTable(marksheet).equals("Updated successfully")) {
			return "Updated successfully";
		} else {
			throw new UpdateResultTableException("MarkSheet table updated but Result table not updated");
		}
	}

	public String updateSem1TheoryByRollNumber(int rollNumber, int sem1Theory) throws UpdateResultTableException, Exception {
		MarkSheet marksheet = getStudentMarksheetByRollNumber(rollNumber);
		marksheet.setSem1Theory(sem1Theory);	
		return verifyUpdation(marksheet);
	}
	
	public String updateSem1PracticalsByRollNumber(int rollNumber, int sem1Practicals) throws UpdateResultTableException, Exception {
		MarkSheet marksheet = getStudentMarksheetByRollNumber(rollNumber);
		marksheet.setSem1Practicals(sem1Practicals);	
		return verifyUpdation(marksheet);
	}
	
	public String updateSem2TheoryByRollNumber(int rollNumber, int sem2Theory) throws UpdateResultTableException, Exception {
		MarkSheet marksheet = getStudentMarksheetByRollNumber(rollNumber);
		marksheet.setSem2Theory(sem2Theory);
		return verifyUpdation(marksheet);
	}
	
	public String updateSem2PracticalsByRollNumber(int rollNumber, int sem2Practicals) throws UpdateResultTableException, Exception {
		MarkSheet marksheet = getStudentMarksheetByRollNumber(rollNumber);
		marksheet.setSem2Practicals(sem2Practicals);
		return verifyUpdation(marksheet);
	}
}
