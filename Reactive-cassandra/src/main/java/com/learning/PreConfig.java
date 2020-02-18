package com.learning;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learning.model.Student;
import com.learning.repository.StudentRepository;

@Configuration
public class PreConfig {

	@Bean 
	public CommandLineRunner dataLoader(StudentRepository studentRepository) { 
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("StudentRepository insert pre data");
				
				Student preS1 = new Student(305, "Tom305", 35);
				Student preS2 = new Student(306, "Tom306", 36);
				Student preS3 = new Student(302, "Tom302", 32);
				
				List<Student> preList = new ArrayList<>();
				//preList.add(preS1);
				preList.add(preS2);
				preList.add(preS3);
				
				studentRepository.insert(preList).subscribe();	
				studentRepository.save(preS1).subscribe();
			}
		};
	 }
}
