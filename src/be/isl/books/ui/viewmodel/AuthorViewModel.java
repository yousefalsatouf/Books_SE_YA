package be.isl.books.ui.viewmodel;

import be.isl.books.entity.Author;

public class AuthorViewModel {
    private Author author;

    public AuthorViewModel(Author author) {
        this.author = author;
    }

    public int getAuthorId() {
        return author.getAuthorId();
    }

    public String getFullName() {
        return author.getFirstname() + " " + author.getFirstname();
    }

    public String getEmail() {
        return author.getEmail();
    }

    public String getDateOfBirth() {
        return author.getDateOfBirth().toString();
    }

    @Override
    public String toString() {
        return "Author ID: " + getAuthorId() + " | Name: " + getFullName() + " | Email: " + getEmail() + " | Date of Birth: " + getDateOfBirth();
    }
}
