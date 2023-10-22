package be.isl.books.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisher_id;
    private String name;

    private java.sql.Date insertedTs;
    private java.sql.Date updatedTs;
    // Getters and setters
    public int getPublisherId() {
        return publisher_id;
    }

    public void setPublisherId(int publisherId) {
        this.publisher_id = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Override
    public String toString() {
        return "Publisher: " + name;
    }
}
