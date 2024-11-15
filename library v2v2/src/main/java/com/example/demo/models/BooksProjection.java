package com.example.demo.models;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.Books;

@Projection(name= "title" , types= {Books.class})
public interface BooksProjection {
	public String getTitle();

}




