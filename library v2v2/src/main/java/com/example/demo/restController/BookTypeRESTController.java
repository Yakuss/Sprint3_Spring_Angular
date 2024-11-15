package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.BookType;
import com.example.demo.repos.BookTypeRepository;

@RestController
@RequestMapping("/api/cat")
@CrossOrigin("*")

public class BookTypeRESTController {

	@Autowired
	BookTypeRepository booktypeRepository;
	
	@GetMapping
	public List<BookType> getAllCategories()
	{
	return booktypeRepository.findAll();
	}

	@GetMapping("/{id}")
	public BookType getBookTypesById(@PathVariable("id") Long id){
		return booktypeRepository.findById(id).get();
	}
	
}
