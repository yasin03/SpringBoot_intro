package com.tpe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Student Controller";
		
	}
	
	@PostMapping
	public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student){
		studentService.createStudent(student);
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "Student created succesfully");
		map.put("status", "true");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAll(){
		List<Student> students = studentService.getAll();
		return ResponseEntity.ok(students);
	}
	
	@GetMapping("/query")
	public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){
		Student student =  studentService.findStudent(id);
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentWithPath(@PathVariable("id") Long id){
		Student student =  studentService.findStudent(id);
		return ResponseEntity.ok(student);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable("id") Long id){
		studentService.deleteStudent(id);
		Map<String, String> map = new HashMap<>();
		map.put("message", "Student delete succesfully");
		map.put("status", "true");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	
}
