package com.example.demo.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(nullable = false)
    private boolean favorite;

    @Column(name = "image_url")
    private String imageUrl;
    
    
    @ManyToOne
    private BookType bookType;
    
    /*@OneToOne
    private Image image;*/

    @OneToMany (mappedBy = "book")
    private List<Image> images;
    
    private String imagePath;

    

   /* public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}*/

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	// No-argument constructor (required by JPA)
    public Books() {
    }

    // Parameterized constructor
    public Books(String title, String author, LocalDate publicationDate, String pdfUrl, boolean favorite, String imageUrl, BookType bookType) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.pdfUrl = pdfUrl;
        this.favorite = favorite;
        this.imageUrl = imageUrl;
        this.bookType = bookType;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    
    public BookType getBookType() {
        return bookType;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
    
    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                ", pdfUrl='" + pdfUrl + '\'' +
                ", favorite=" + favorite +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
