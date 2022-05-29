package com.example.demo.services.impl;

import java.util.List;
import java.util.concurrent.Flow.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.data.jdbc.core.convert.EntityRowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Course;
import com.example.demo.services.CoursesService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoursesServiceImpl implements CoursesService{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	
	@Override
	public Flux<Course> getAll() {
		String query="SELECT * FROM courses";
		return Flux.fromIterable(()->{
			return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Course.class)).iterator();
		});	
	}

	@Override
	public Mono<Course> getById(Integer id) {
		String query="SELECT * FROM courses WHERE id="+id;
		return Mono.fromSupplier(()->{
			try {
				Course course = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Course.class));
				return course;
			}catch(Exception e) {
				return null;
			}
		});
	}

	@Override
	public Mono<Course> post(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Course> patch(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> del(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
