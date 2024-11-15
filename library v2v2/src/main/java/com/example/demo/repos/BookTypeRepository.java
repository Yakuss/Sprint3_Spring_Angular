package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.models.BookType;
@RepositoryRestResource(path ="cat")
@CrossOrigin("*")
public interface BookTypeRepository extends JpaRepository <BookType , Long>{
	

}
