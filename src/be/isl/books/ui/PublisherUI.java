package be.isl.books.ui;

import be.isl.books.business.PublisherManager;
import be.isl.books.entity.Publisher;
import be.isl.books.ui.viewmodel.PublisherViewModel;
import be.isl.books.ui.util.UIUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PublisherUI {
    public static void main(String[] args)  {
            PublisherManager publisherManager = new PublisherManager();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Publisher Management Menu:");
                System.out.println("1. Add Publisher");
                System.out.println("2. View All Publishers");
                System.out.println("3. Search Publishers");
                System.out.println("4. Update Publisher");
                System.out.println("5. Delete Publisher");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        Publisher newPublisher = capturePublisherDetails(scanner);
                        publisherManager.addPublisher(newPublisher);
                        System.out.println("Publisher added successfully.");
                        break;

                    case 2:
                        List<Publisher> publishers = publisherManager.getAllPublishers();
                        displayPublishers(publishers);
                        break;

                    case 3:
                        String searchCriteria = UIUtil.getStringInput(scanner, "The Search Functionality is not implemented yet due to the limited time :-(");
                        /*List<Publisher> searchResults = publisherManager.searchPublishers(searchCriteria);
                        displayPublishers(searchResults);*/
                        break;

                    case 4:
                        int publisherIdToUpdate = UIUtil.getIntInput(scanner, "Enter Publisher ID to update: ");
                        Publisher updatedPublisher = capturePublisherDetails(scanner);
                        updatedPublisher.setPublisherId(publisherIdToUpdate);
                        publisherManager.updatePublisher(updatedPublisher);
                        System.out.println("Publisher updated successfully.");
                        break;

                    case 5:
                        int publisherIdToDelete = UIUtil.getIntInput(scanner, "Enter Publisher ID to delete: ");
                        publisherManager.deletePublisher(publisherIdToDelete);
                        System.out.println("Publisher deleted successfully.");
                        break;

                    case 6:
                        System.out.println("Closing connection to DB ...");
                        publisherManager.closeDatabaseConnection();
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
    }

    private static Publisher capturePublisherDetails(Scanner scanner) {
        System.out.print("Enter Publisher Name: ");
        String name = scanner.nextLine();

        Publisher newPublisher = new Publisher();
        newPublisher.setName(name);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        newPublisher.setInsertedIs(sqlDate);
        newPublisher.setUpdatedTs(sqlDate);

        return newPublisher;
    }

    private static void displayPublishers(List<Publisher> publishers) {
        System.out.println("Publishers:");
        for (Publisher publisher : publishers) {
            PublisherViewModel viewModel = new PublisherViewModel(publisher);
            System.out.println(viewModel);
        }
    }
}
