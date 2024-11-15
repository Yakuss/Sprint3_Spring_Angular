package com.example.demo.restController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Books;
import com.example.demo.models.Image;
import com.example.demo.service.BooksService;
import com.example.demo.service.ImageService;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRestController {
	@Autowired
	ImageService imageService;
	@Autowired
	BooksService booksService;

	@PostMapping("/upload")
	public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		return imageService.uplaodImage(file);
	}

	@GetMapping("/get/info/{id}")
	public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
		return imageService.getImageDetails(id);
	}

	@GetMapping("/load/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
		return imageService.getImage(id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteImage(@PathVariable("id") Long id) {
		imageService.deleteImage(id);
	}

	@PutMapping("/update")
	public Image UpdateImage(@RequestParam("image") MultipartFile file) throws IOException {
		return imageService.uplaodImage(file);
	}

	@PostMapping(value = "/uplaodImageBook/{id}")
	public Image uploadMultiImages(@RequestParam("image") MultipartFile file, @PathVariable("id") Long idProd)
			throws IOException {
		return imageService.uplaodImageBook(file, idProd);
	}

	@GetMapping("/getImagesBook/{id}")
	public List<Image> getImagesProd(@PathVariable("id") Long idProd) throws IOException {
		return imageService.getImagesParBook(idProd);
	}
	
	//fs
	
	
	@PostMapping("/uploadFS/{id}")
	public void uploadImageFS(@RequestParam("image") MultipartFile file, @PathVariable Long id)
			throws IOException {
		Books p = booksService.getBooks(id);
		p.setImagePath(id + ".jpg");

		Files.write(Paths.get(System.getProperty("user.home") + "/images/" + p.getImagePath()), file.getBytes());
		booksService.saveBooks(p);

	}
	
	@GetMapping(value = "/loadfromFS/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImageFS(@PathVariable Long id) throws IOException {

		Books p = booksService.getBooks(id);
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/images/" + p.getImagePath()));
	}

 
	
	
	
}
