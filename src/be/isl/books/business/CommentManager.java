package be.isl.books.business;

import be.isl.books.entity.Author;
import be.isl.books.entity.Book;
import be.isl.books.entity.Comment;
import be.isl.books.entity.Publisher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentManager {
    private static final String url = "jdbc:postgresql://titus.isl.be:5432/books2023";
    private static final String user = "ue";
    private static final String password = "uetest2023";

    private final Connection connection;

    public CommentManager() {
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
   public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Comment";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setCommentId(resultSet.getInt("comment_id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setStars(resultSet.getInt("stars"));
                comment.setHide(resultSet.getBoolean("hide"));
                comments.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public Comment getCommentById(int commentId) {
        Comment comment = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try  {
            String sql = "SELECT * FROM Comment WHERE comment_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, commentId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                comment = new Comment();
                comment.setCommentId(resultSet.getInt("comment_id"));
                comment.setBookId(resultSet.getInt("book_id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setStars(resultSet.getInt("stars"));
                comment.setHide(resultSet.getBoolean("hide"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public void addComment(Comment comment) {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO Comment (book_id, comment, stars, hide) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, comment.getBookId());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setInt(3, comment.getStars());
            preparedStatement.setBoolean(4, comment.isHide());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateComment(Comment comment) {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE Comment SET book_id = ?, comment = ?, stars = ?, hide = ? WHERE comment_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, comment.getBookId());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setInt(3, comment.getStars());
            preparedStatement.setBoolean(4, comment.isHide());
            preparedStatement.setInt(5, comment.getCommentId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteComment(int commentId) {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM Comment WHERE comment_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, commentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public Book getBookById(int bookId) {
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
    }*/

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
