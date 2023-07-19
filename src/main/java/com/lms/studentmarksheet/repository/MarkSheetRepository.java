package com.lms.studentmarksheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.studentmarksheet.entity.MarkSheet;

public interface MarkSheetRepository extends JpaRepository<MarkSheet, Integer> {

}
