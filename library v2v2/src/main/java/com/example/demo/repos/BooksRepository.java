package com.example.demo.repos;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.example.demo.models.BookType;
import com.example.demo.models.Books;

//@Repository
@RepositoryRestResource(path = "rest")
public interface BooksRepository extends JpaRepository<Books, Long> {

	List<Books> findByTitle(String title);
	List<Books> findByTitleContains(String title);
	
	
	/*@Query("select b from Books b where b.title like %?1% and b.author like %?2%")
	List<Books> findByTitleAuthor(String title, String author); or ------> next line*/

	@Query("select b from Books b where b.title like %:title% and b.author like %:author%")
	List<Books> findByTitleAuthor(@Param("title") String title , @Param("author") String author);
	
	
	
	@Query("select b from Books b where b.bookType = ?1")
	List<Books> findByBookType(BookType type);
	
	
	
	List<Books> findByBookTypeId(Long id);
	
	
	List<Books> findByOrderByTitleAsc();
	
	@Query("select b from Books b order by b.title ASC, b.author DESC")
	List<Books> TrierBooksTitreAuthor();

	
	
	
    
}