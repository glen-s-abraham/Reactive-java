package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Course;
import com.example.demo.services.CoursesService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CourseController {
	
	@Autowired
	CoursesService coursesService;
	
	@GetMapping(value="/ping")
	Mono<String> ping(){
		return Mono.just("Alive");
	}
	
	@GetMapping("/courses")
	Flux<Course> getAll(){
		return coursesService.getAll(); 
	}
	
	@GetMapping("/courses/{id}")
	Mono<Course> getAll(@PathVariable Integer id){
		System.out.println(id);
		return coursesService.getById(id); 
	}
}
