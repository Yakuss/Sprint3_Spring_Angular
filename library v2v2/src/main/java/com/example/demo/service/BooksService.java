package com.example.demo.service;

import java.util.List;

import com.example.demo.models.BookType;
import com.example.demo.models.Books;

public interface BooksService {
	Books saveBooks(Books b);
	Books UpdateBooks(Books b);
	void deletebooks(Books b);
	void deletebooksById(long id);
	Books getBooks(Long id);
	
	List<Books> getAllBooks();
	List<Books> findByTitle(String title);
	List<Books> findByTitleContains(String title);
	List<Books> findByTitleAuthor (String title, String author);
	List<Books> findByBookType (BookType type);
	List<Books> findByBookTypeId(Long id);
	List<Books> findByOrderByTitleAsc();
	List<Books> TrierBooksTitreAuthor();

}
