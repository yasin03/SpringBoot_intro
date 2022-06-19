package com.tpe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	
	@Override
	public List<Student> getAll() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> findStudents(String lastName) {
		// return studentRepository.findByLastName(lastName);
		// return new ArrayList<Student>();
		return studentRepository.findByLastName(lastName);
	}

	@Override
	public Student findStudent(Long id) throws ResourceNotFoundException {
//		Student student = studentRepository.findById(id);
//		if(student==null) {
//			throw new ResourceNotFoundException("Student not found with id : "+id);
//		}
//		return student;
		return studentRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Student not found with id : "+ id));
		}

	@Override
	public void createStudent(Student student) {
		// studentRepository.create(student);
		 
		if(studentRepository.existsByEmail(student.getEmail())) {
			throw new ConflictException("Email already exist!");
		}
		studentRepository.save(student);

	}

	@Override
	public void updateStudent(Student student) {
		// studentRepository.update(student);

	}

	@Override
	public void deleteStudent(Long id) {
		// studentRepository.delete(id);
		Student student = findStudent(id);
		studentRepository.delete(student);
	}

}
