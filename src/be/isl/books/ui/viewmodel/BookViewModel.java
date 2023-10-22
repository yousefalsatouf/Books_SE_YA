package be.isl.books.ui.viewmodel;

import be.isl.books.entity.Book;

public class BookViewModel {
    private Book book;

    public BookViewModel(Book book) {
        this.book = book;
    }

    public int getBookId() {
        return book.getBookId();
    }

    public String getTitle() {
        return book.getTitle();
    }

    public String getDescription() {
        return book.getDescription();
    }

    // Implement getters for other book properties

    @Override
    public String toString() {
        return "Book ID: " + getBookId() + " | Title: " + getTitle() + " | Description: " + getDescription();
        // Include other book properties in the output
    }
}
