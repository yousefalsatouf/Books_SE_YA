package be.isl.books.business;

import be.isl.books.entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {
    private final Connection connection;

    public AuthorManager() {
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

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Author";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Author author = new Author();
                author.setAuthorId(resultSet.getInt("author_id"));
                author.setFirstname(resultSet.getString("firstname"));
                author.setLastname(resultSet.getString("lastname"));
                author.setEmail(resultSet.getString("email"));
                author.setDateOfBirth(resultSet.getDate("date_of_birth"));
                author.setInsertedIs((resultSet.getDate("inserted_ts")));
                author.setInsertedIs((resultSet.getDate("updated_ts")));
                authors.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author getAuthorById(int authorId) {
        Author author = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try  {
            String sql = "SELECT * FROM Author WHERE author_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            preparedStatement.setInt(1, authorId);
            if (resultSet.next()) {
                author = new Author();
                author.setAuthorId(resultSet.getInt("author_id"));
                author.setFirstname(resultSet.getString("firstname"));
                author.setLastname(resultSet.getString("lastname"));
                author.setEmail(resultSet.getString("email"));
                author.setDateOfBirth(resultSet.getDate("date_of_birth"));
                author.setInsertedIs((resultSet.getDate("inserted_ts")));
                author.setInsertedIs((resultSet.getDate("updated_ts")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    public void addAuthor(Author author) {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO Author (firstname, lastname, email, date_of_birth, inserted_ts) VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, author.getFirstname());
            preparedStatement.setString(2, author.getLastname());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setDate(4, author.getDateOfBirth());
            preparedStatement.setDate(5, author.getInsertedIs());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAuthor(Author author) {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Author SET firstname = ?, lastname = ?, email = ?, date_of_birth = ?, updated_ts = ? WHERE author_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, author.getFirstname());
            preparedStatement.setString(2, author.getLastname());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setDate(4, author.getDateOfBirth());
            preparedStatement.setDate(5, author.getUpdatedTs());
            preparedStatement.setInt(6, author.getAuthorId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAuthor(int authorId) {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM Author WHERE author_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, authorId);
            preparedStatement.executeUpdate();
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
