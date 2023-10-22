package be.isl.books.ui.viewmodel;

import be.isl.books.entity.Publisher;

public class PublisherViewModel {
    private Publisher publisher;

    public PublisherViewModel(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getPublisherId() {
        return publisher.getPublisherId();
    }

    public String getName() {
        return publisher.getName();
    }


    // Here where the view will show on your terminal in a nice way for listing table data

    @Override
    public String toString() {
        return "Publisher ID: " + getPublisherId() + " | Name: " + getName();
        // Include other publisher properties in the output
    }
}
