package be.isl.books.ui;

import be.isl.books.business.BookManager;
import be.isl.books.entity.Book;
import be.isl.books.ui.viewmodel.BookViewModel;
import be.isl.books.ui.util.UIUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookUI {

    public static void main(String[] args) {
            BookManager bookManager = new BookManager();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Book Management Menu:");
                System.out.println("1. Add Book");
                System.out.println("2. View All Books");
                System.out.println("3. Search Books");
                System.out.println("4. Update Book");
                System.out.println("5. Delete Book");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        Book newBook = captureBookDetails(scanner);
                        bookManager.addBook(newBook);
                        System.out.println("Book added successfully.");
                        break;

                    case 2:
                        List<Book> books = bookManager.getAllBooks();
                        displayBooks(books);
                        break;

                    case 3:
                        String searchData = UIUtil.getStringInput(scanner, "The Search Functionality is not implemented yet due to the limited time :-(");
                        /*List<Book> searchResults = bookManager.searchBooks(searchData);
                        displayBooks(searchResults);*/
                        break;

                    case 4:
                        int bookIdToUpdate = UIUtil.getIntInput(scanner, "Enter Book ID to update: ");
                        Book updatedBook = captureBookDetails(scanner);
                        updatedBook.setBookId(bookIdToUpdate);
                        bookManager.updateBook(updatedBook);
                        System.out.println("Book updated successfully.");
                        break;

                    case 5:
                        int bookIdToDelete = UIUtil.getIntInput(scanner, "Enter Book ID to delete: ");
                        bookManager.deleteBook(bookIdToDelete);
                        System.out.println("Book deleted successfully.");
                        break;

                    case 6:
                        System.out.println("Closing connection to DB ...");
                        bookManager.closeDatabaseConnection();
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

    }

    private static Book captureBookDetails(Scanner scanner) {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Number of Pages: ");
        int nbPages = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter IsBn: ");
        String isBn = scanner.nextLine();
        System.out.print("Enter The Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Publication Date (YYYY-MM-DD): ");
        String publicationDate = scanner.nextLine();

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setDescription(description);
        newBook.setNbPages(nbPages);
        newBook.setIsbn(isBn);
        newBook.setPrice(price);
        newBook.setPublicationDate(java.sql.Date.valueOf(publicationDate));

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        newBook.setInsertedIs(sqlDate);
        newBook.setUpdatedTs(sqlDate);

        return newBook;
    }

    private static void displayBooks(List<Book> books) {
        System.out.println("Books:");
        for (Book book : books) {
            BookViewModel viewModel = new BookViewModel(book);
            System.out.println(viewModel);
        }
    }
}
