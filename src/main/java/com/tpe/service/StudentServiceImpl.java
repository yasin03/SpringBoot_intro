package com.tpe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
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
	public void updateStudent(Long id, StudentDTO studentDTO) {
		// studentRepository.update(student);
		Boolean emailExist = studentRepository.existsByEmail(studentDTO.getEmail());
		Student student = findStudent(id);
		
		if(emailExist && studentDTO.getEmail().equals(student.getEmail())) {
			throw new ConflictException("Email already exist");
		}
		
		// Student updateStudent = new Student();

		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setGrade(studentDTO.getGrade());
		student.setEmail(studentDTO.getEmail());
		student.setPhoneNumber(studentDTO.getPhoneNumber());
		
		studentRepository.save(student);
		
	}

	@Override
	public void deleteStudent(Long id) {
		// studentRepository.delete(id);
		Student student = findStudent(id);
		studentRepository.delete(student);
	}

	@Override
	public Page<Student> getAllWithPage(Pageable pageable) {

		return studentRepository.findAll(pageable);
	}

	@Override
	public List<Student> findStudents(Integer grade) {

		return studentRepository.findByGrade(grade);
	}

	@Override
	public List<Student> findAllEqualsGrade(Integer grade) {

		return studentRepository.findAllEqualsGrade(grade);
	}

	@Override
	public StudentDTO findStudentDTOById(Long id) {

		return studentRepository.findStudentDTOById(id).
				orElseThrow(()-> new ResourceNotFoundException("Resource not found with id : " + id));
	}




}
