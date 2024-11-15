package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.BookType;
import com.example.demo.models.Books;
import com.example.demo.repos.BooksRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class LibraryApplicationTests {
	
	
	@Autowired
	private BooksRepository  bk;
	
	

	/*
    @Test
    public void testCreateBooks() {
        Books b1 = new Books("The logrhffffff","F. Scott Fitzgerald",LocalDate.of(1925, 4, 10),"https://example.com/greatgatsby.pdf",true,"https://example.com/gatsby.jpg");
        bk.save(b1);
    }*/
    
    @Test
    public void testFindBooks() {
        Books b2= bk.findById(1l).get();
        System.out.println(b2);
    }
    
    @Test
    public void testUpdateBooks() {
        Books b2= bk.findById(1l).get();
        b2.setAuthor("bouasba");
        System.out.println(b2);
        bk.save(b2);
    }
    
    @Test
    public void testDeleteBooks() {
        bk.deleteById(5l);
    }
    
    @Test
    public void testFindAllBooks() {
        List<Books> kotb = bk.findAll();
        for (Books bkk: kotb) {
        	System.out.println(bkk);
        }
    }
    
    @Test
    public void testFindBooksByTitle() {
        List<Books> books = bk.findByTitle("loul");
        books.forEach(System.out::println);
    }
    
    @Test
    public void testFindBooksByTitleContain() {
        List<Books> books = bk.findByTitleContains("lo");
        books.forEach(System.out::println);
    }
    
    
    @Test
    public void testFindBooksByTitleAuthor() {
        List<Books> books = bk.findByTitleAuthor("lou" , "yas");
        books.forEach(System.out::println);
    }
    
    
    @Test
    public void testFindBooksByBookType() {
    	BookType bt = new BookType();
    	bt.setId(1l);
        List<Books> books = bk.findByBookType(bt);
        books.forEach(System.out::println);
    }
    
    
    @Test
    public void testFindBooksByBookTypeId() {
    	
        List<Books> books = bk.findByBookTypeId(2l);
        books.forEach(System.out::println);
    }
    
    @Test
    public void testFindByOrderByTitleAsc() {
    	
        List<Books> books = bk.findByOrderByTitleAsc();
        books.forEach(System.out::println);
    }
    
    @Test
    public void testTrierBooksTitreAuthor() {
    	
        List<Books> books = bk.TrierBooksTitreAuthor();
        books.forEach(System.out::println);
    }
    
} 
