package com.example.demo.pojo;


import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

public class Course {
	@NotNull
	private Integer id;
	@NonNull
	private String title;
	private String description;
	@NonNull
	private String author;
	public Course() {}
	public Course(Integer id, String title, String description, String author) {
		
		
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", description=" + description + ", author=" + author + "]";
	}
	
	
	
}
