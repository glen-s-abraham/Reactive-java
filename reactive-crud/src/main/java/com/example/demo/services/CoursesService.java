package com.example.demo.services;

import java.util.List;

import com.example.demo.pojo.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoursesService {
	
	Flux<Course> getAll();
	Mono<Course> getById(Integer id);
	Mono<Course> post(Course course);
	Mono<Course> patch(Course course);
	Mono<Void> del(Integer id);
	
}
