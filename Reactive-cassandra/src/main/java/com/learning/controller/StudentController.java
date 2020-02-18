package com.learning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learning.model.Student;
import com.learning.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController // rest controller returns json
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@PostConstruct
	public void loadStudent() {
		List<Student> student = new ArrayList<>();
		student.add(new Student(110, "percy11", 11));
		student.add(new Student(120, "percy12", 12));
		System.out.println("in the loadStudent, try to saveAll");
		// studentRepository.insert(student).subscribe(); // subscribe is consuming data. work
		studentRepository.saveAll(student).subscribe(); // both insert and saveAll work
	}
	
	@GetMapping(path="/{id}")
	public Mono<Student> getStudentById(@PathVariable int id){
		System.out.println("in the getStudentById, try to run findById");
		return studentRepository.findById(id);
	}
	
	@GetMapping(path="/all")
	public Flux<Student> getAllStudent(){
		System.out.println("in the getAllStudent, try to run getAllStudent");
		return studentRepository.findAll();
	}
	
	// save a student
	@PostMapping(path="/save", consumes="application/json", produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveAStudent(@RequestBody Student student) throws Exception {
		System.out.println("in the saveAStudent, try to save a student=" + student.toString());
		//Mono<Student> monoStudent = Mono.just(student);
		//System.out.println("in the saveAStudent, try to save a monostudent=" + monoStudent.toString());
	    // studentRepository.save(student);
		studentRepository.insert(student).subscribe();
	}
}
