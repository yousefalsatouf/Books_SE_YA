package be.isl.books.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private java.sql.Date insertedTs;
    private Date updatedTs;
}
