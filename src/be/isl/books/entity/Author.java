package be.isl.books.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int author_id;
    private String firstname;
    private String lastname;
    private String email;
    private java.sql.Date date_of_birth;
    private java.sql.Date insertedTs;
    private java.sql.Date updatedTs;


    public int getAuthorId() {
        return author_id;
    }

    public void setAuthorId(int authorId) {
        this.author_id = authorId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Date getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.date_of_birth = dateOfBirth;
    }

    public java.sql.Date getInsertedIs() {
        return insertedTs;
    }

    public void setInsertedIs(java.sql.Date  date) {
        this.insertedTs =  date;
    }

    public java.sql.Date getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(java.sql.Date  date) {
        this.updatedTs = date;
    }

    @Override
    public String toString() {
        return "Author: " + firstname + " " + lastname;
    }
}
