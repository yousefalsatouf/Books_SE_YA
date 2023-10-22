package be.isl.books.business;

import be.isl.books.entity.Book;
import be.isl.books.entity.Publisher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private static final String url = "jdbc:postgresql://titus.isl.be:5432/books2023";
    private static final String user = "ue";
    private static final String password = "uetest2023";

    private final Connection connection;

    public BookManager() {
        String url = "jdbc:postgresql://titus.isl.be:5432/books2023";
        String user = "ue";
        String password = "uetest2023";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM Book";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setDescription(resultSet.getString("description"));
                book.setNbPages(resultSet.getInt("nb_pages"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPrice(resultSet.getDouble("price"));
                book.setPublicationDate(resultSet.getDate("publication_date"));

                // Load the associated publisher (assuming PublisherManager is available)
                int publisherId = resultSet.getInt("publisher_id");
                Publisher publisher = new PublisherManager().getPublisherById(publisherId);
                book.setPublisher(publisher);

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getBookById(int bookId) {
        Book book = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM Book WHERE book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, bookId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        book = new Book();
                        book.setBookId(resultSet.getInt("book_id"));
                        book.setTitle(resultSet.getString("title"));
                        book.setDescription(resultSet.getString("description"));
                        book.setNbPages(resultSet.getInt("nb_pages"));
                        book.setIsbn(resultSet.getString("isbn"));
                        book.setPrice(resultSet.getDouble("price"));
                        book.setPublicationDate(resultSet.getDate("publication_date"));

                        // Load the associated publisher (assuming PublisherManager is available)
                        int publisherId = resultSet.getInt("publisher_id");
                        Publisher publisher = new PublisherManager().getPublisherById(publisherId);
                        book.setPublisher(publisher);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void addBook(Book book) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Book (title, description, nb_pages, isbn, price, publication_date, publisher_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getDescription());
                preparedStatement.setInt(3, book.getNbPages());
                preparedStatement.setString(4, book.getIsbn());
                preparedStatement.setDouble(5, book.getPrice());
                preparedStatement.setDate(6, new java.sql.Date(book.getPublicationDate().getTime()));
                preparedStatement.setInt(7, book.getPublisher().getPublisherId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE Book " +
                    "SET title = ?, description = ?, nb_pages = ?, isbn = ?, price = ?, publication_date = ?, publisher_id = ? " +
                    "WHERE book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getDescription());
                preparedStatement.setInt(3, book.getNbPages());
                preparedStatement.setString(4, book.getIsbn());
                preparedStatement.setDouble(5, book.getPrice());
                preparedStatement.setDate(6, new java.sql.Date(book.getPublicationDate().getTime()));
                preparedStatement.setInt(7, book.getPublisher().getPublisherId());
                preparedStatement.setInt(8, book.getBookId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM Book WHERE book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, bookId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
