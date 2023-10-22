package be.isl.books.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private int bookId;

    private String comment;
    private int stars;
    private boolean hide;
    private java.sql.Date insertedTs;
    private java.sql.Date updatedTs;

    // Getters and setters
    public int getCommentId() {
        return comment_id;
    }

    public void setCommentId(int commentId) {
        this.comment_id = commentId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public java.sql.Date getInsertedIs() {
        return insertedTs;
    }

    public void setInsertedIs(java.sql.Date date) {
        this.insertedTs = date;
    }

    public java.sql.Date getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(java.sql.Date date) {
        this.updatedTs = date;
    }
}
