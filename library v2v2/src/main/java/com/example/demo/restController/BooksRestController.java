package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Books;
import com.example.demo.service.BooksService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BooksRestController {
	
	@Autowired
	BooksService booksService ;
	
	@GetMapping(path="all")
	public List<Books> getAllBooks(){
		return booksService.getAllBooks();
	}
	
	@GetMapping("/getbyid/{id}")
	public Books getBooksById(@PathVariable("id") Long id){
		return booksService.getBooks(id);
	}
	
	@PostMapping(path="/addbook")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Books CreateBook(@RequestBody Books book){
		return booksService.saveBooks(book);
	}
	
	@PutMapping(path="/updatebook")
	public Books updateBook(@RequestBody Books bookDetails) {
	    return booksService.UpdateBooks(bookDetails);
	}
	
	@DeleteMapping(value="/delbook/{id}")
	public void deleteProduit(@PathVariable("id") Long id) {
	    booksService.deletebooksById( id );
	}
	
	@GetMapping(value="/booktyped/{id}")
	public List<Books> getBooksByTypeId(@PathVariable("id") Long id){
		return booksService.findByBookTypeId(id);
	}
	
	@GetMapping(value="/booksByTitle/{title}")
	public List<Books> findBytitleContains(@PathVariable("title") String title){
		return booksService.findByTitleContains(title);
	}
	

	

	

	

}
