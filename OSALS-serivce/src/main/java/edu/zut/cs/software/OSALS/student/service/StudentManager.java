package edu.zut.cs.software.OSALS.student.service;

import edu.zut.cs.software.OSALS.student.domain.Student;
import edu.zut.cs.software.base.service.GenericManager;

import java.util.List;

public interface StudentManager extends GenericManager<Student, Long> {
	
	List<Student> getAll();

	Student findByStudentname(String studentname);
}