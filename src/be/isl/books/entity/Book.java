package be.isl.books.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    private String title;
    private String description;
    private int nb_pages;
    private String isbn;
    private double price;
    @Temporal(TemporalType.DATE)
    private Date publication_date;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    // Getters and setters
    public int getBookId() {
        return book_id;
    }

    public void setBookId(int bookId) {
        this.book_id = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbPages() {
        return nb_pages;
    }

    public void setNbPages(int nbPages) {
        this.nb_pages = nbPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublicationDate() {
        return publication_date;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publication_date = publicationDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    @Override
    public String toString() {
        return "Book: " + title;
    }
}
