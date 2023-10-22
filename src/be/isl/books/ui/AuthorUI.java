package be.isl.books.ui;

import be.isl.books.business.AuthorManager;
import be.isl.books.entity.Author;
import be.isl.books.ui.viewmodel.AuthorViewModel;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class AuthorUI {
    public static void main(String[] args) {
        AuthorManager authorManager = new AuthorManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Author Management Menu:");
            System.out.println("1. Add Author");
            System.out.println("2. View All Authors");
            System.out.println("3. Search Authors");
            System.out.println("4. Update Author");
            System.out.println("5. Delete Author");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Author newAuthor = captureAuthorDetails(scanner);
                    authorManager.addAuthor(newAuthor);
                    System.out.println("Author added successfully.");
                    break;

                case 2:
                    List<Author> authors = authorManager.getAllAuthors();
                    displayAuthors(authors);
                    break;

                case 3:
                    System.out.print("The Search Functionality is not implemented yet due to the limited time :-(");
                    //String searchData = scanner.nextLine();
                        /*List<Author> searchResults = authorManager.searchAuthors(searchData);
                        displayAuthors(searchResults);*/
                    break;

                case 4:
                    int authorIdToUpdate = captureAuthorId(scanner);
                    Author updatedAuthor = captureAuthorDetails(scanner);
                    updatedAuthor.setAuthorId(authorIdToUpdate);
                    authorManager.updateAuthor(updatedAuthor);
                    System.out.println("Author updated successfully.");
                    break;

                case 5:
                    int authorIdToDelete = captureAuthorId(scanner);
                    authorManager.deleteAuthor(authorIdToDelete);
                    System.out.println("Author deleted successfully.");
                    break;

                case 6:
                    System.out.println("Closing connection to DB ...");
                    authorManager.closeDatabaseConnection();

                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static Author captureAuthorDetails(Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        Author newAuthor = new Author();
        newAuthor.setDateOfBirth(java.sql.Date.valueOf(dateOfBirth));
        newAuthor.setFirstname(firstName);
        newAuthor.setLastname(lastName);
        newAuthor.setEmail(email);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        newAuthor.setInsertedIs(sqlDate);
        newAuthor.setUpdatedTs(sqlDate);


        return newAuthor;

    }

    private static int captureAuthorId(Scanner scanner) {
        System.out.print("Enter Author ID: ");
        return scanner.nextInt();
    }

    private static void displayAuthors(List<Author> authors) {
        System.out.println("Authors:");
        for (Author author : authors) {
            AuthorViewModel viewModel = new AuthorViewModel(author);
            System.out.println(viewModel);
        }
    }
}
