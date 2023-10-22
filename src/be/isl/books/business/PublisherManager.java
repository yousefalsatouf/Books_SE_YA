package be.isl.books.business;

import be.isl.books.entity.Publisher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherManager {
    private static final String url = "jdbc:postgresql://titus.isl.be:5432/books2023";
    private static final String user = "ue";
    private static final String password = "uetest2023";

    private final Connection connection;

    public PublisherManager() {
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

    public List<Publisher> getAllPublishers()  {
        List<Publisher> publishers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try  {
            String sql = "SELECT * FROM Publisher";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publisher publisher = new Publisher();
                publisher.setPublisherId(resultSet.getInt("publisher_id"));
                publisher.setName(resultSet.getString("name"));
                publishers.add(publisher);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    public Publisher getPublisherById(int publisherId) {
        Publisher publisher = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM Publisher WHERE publisher_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, publisherId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                publisher = new Publisher();
                publisher.setPublisherId(resultSet.getInt("publisher_id"));
                publisher.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publisher;
    }

    public void addPublisher(Publisher publisher) {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO Publisher (name) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePublisher(Publisher publisher) {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Publisher SET name = ? WHERE publisher_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setInt(2, publisher.getPublisherId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePublisher(int publisherId) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM Publisher WHERE publisher_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, publisherId);
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
