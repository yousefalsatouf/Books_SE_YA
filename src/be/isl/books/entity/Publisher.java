package be.isl.books.entity;

import javax.persistence.*;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisher_id;
    private String name;

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
    @Override
    public String toString() {
        return "Publisher: " + name;
    }
}
