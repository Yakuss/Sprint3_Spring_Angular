package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.BookType;
import com.example.demo.models.Books;
import com.example.demo.repos.BooksRepository;
import com.example.demo.repos.ImageRepository;

@Service
public class BooksServiceImpl implements BooksService{

	
	@Autowired
	BooksRepository BooksRepository;
	@Autowired
	ImageRepository imageRepository;
	
	@Override
	public Books saveBooks(Books b) {
		return BooksRepository.save(b);
	}

	/*@Override
	public Books UpdateBooks(Books b) {
		return BooksRepository.save(b);
	}*/
	
	@Override
	public Books UpdateBooks(Books p) {
		//Long oldBookImageId = this.getBooks(p.getId()).getImage().getIdImage();
		//Long newBookImageId = p.getImage().getIdImage();
		Books prodUpdated = BooksRepository.save(p);
		//if (oldBookImageId != newBookImageId) // si l'image a été modifiée
			//imageRepository.deleteById(oldBookImageId);
		return prodUpdated;
	}
	
	

	@Override
	public void deletebooks(Books b) {
		BooksRepository.delete(b);
		
	}

	@Override
	public void deletebooksById(long id) {
		
		//delete the image first ----------->
		Books b = getBooks(id);
		try {
			Files.delete(Paths.get(System.getProperty("user.home") + "/images/" + b.getImagePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BooksRepository.deleteById(id);

	}

	@Override
	public Books getBooks(Long id) {
		return BooksRepository.findById(id).get();
	}

	@Override
	public List<Books> getAllBooks() {
		return BooksRepository.findAll();
	}
	
	
	/*--------------<*/

	@Override
	public List<Books> findByTitle(String title) {
		return BooksRepository.findByTitle(title);
	}
	

	@Override
	public List<Books> findByTitleContains(String title) {
		return BooksRepository.findByTitleContains(title);
	}

	@Override
	public List<Books> findByTitleAuthor(String title, String author) {
		return BooksRepository.findByTitleAuthor(title, author);
	}

	@Override
	public List<Books> findByBookType(BookType type) {
		return BooksRepository.findByBookType(type);
	}

	@Override
	public List<Books> findByBookTypeId(Long id) {
		return BooksRepository.findByBookTypeId(id);
	}

	@Override
	public List<Books> findByOrderByTitleAsc() {
		return BooksRepository.findByOrderByTitleAsc();
	}

	@Override
	public List<Books> TrierBooksTitreAuthor() {
		return BooksRepository.TrierBooksTitreAuthor();
	}
	
	

	
	
	
	

}
