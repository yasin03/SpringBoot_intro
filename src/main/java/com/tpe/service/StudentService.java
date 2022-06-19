package com.tpe.service;

import java.util.List;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;

public interface StudentService {
	
	List<Student> getAll();
	List<Student> findStudents(String lastName);
	Student findStudent(Long id) throws ResourceNotFoundException;
	void createStudent(Student student);
	void updateStudent(Student student);
	void deleteStudent(Long id);
	

}
