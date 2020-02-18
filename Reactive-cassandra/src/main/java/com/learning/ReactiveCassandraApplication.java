package com.learning;

//import java.util.ArrayList;
//import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.learning.model.Student;
//import com.learning.repository.*;

@SpringBootApplication
public class ReactiveCassandraApplication { // implements CommandLineRunner 

	/*@Autowired
	private StudentRepository studentRepository;*/
	
	public static void main(String[] args) {
		SpringApplication.run(ReactiveCassandraApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		final Student s1 = new Student(220, "Tom", 21);
		final Student s2 = new Student(222, "Tom", 22);
		final Student s3 = new Student(223, "Tom", 23);
		
		List<Student> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		System.out.println("in the run." + "run insert and subscribe");
		studentRepository.insert(list).subscribe();		
		
		System.out.println("in the run." + "run findAll");
		studentRepository.findAll().log().map(Student::getId).subscribe(l -> System.out.println("in run. Student id=" + l));
		System.out.println("in the run." + "run findName");
		studentRepository.findByName("percy5").log().map(Student::getId).subscribe(l -> System.out.println("in run. Student2 id=" + l));
	}*/

}
