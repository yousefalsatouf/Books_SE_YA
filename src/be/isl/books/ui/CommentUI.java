package be.isl.books.ui;

import be.isl.books.business.CommentManager;
import be.isl.books.entity.Comment;
import be.isl.books.ui.viewmodel.CommentViewModel;
import be.isl.books.ui.util.UIUtil;

import java.util.List;
import java.util.Scanner;

public class CommentUI {
    public static void main(String[] args) {
            CommentManager commentManager = new CommentManager();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Comment Management Menu:");
                System.out.println("1. Add Comment");
                System.out.println("2. View All Comments");
                System.out.println("3. Search Comments");
                System.out.println("4. Update Comment");
                System.out.println("5. Delete Comment");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        Comment newComment = captureCommentDetails(scanner);
                        commentManager.addComment(newComment);
                        System.out.println("Comment added successfully.");
                        break;

                    case 2:
                        List<Comment> comments = commentManager.getAllComments();
                        displayComments(comments);
                        break;

                    case 3:
                        String searchCriteria = UIUtil.getStringInput(scanner, "The Search Functionality is not implemented yet due to the limited time :-(");
                        /*List<Comment> searchResults = commentManager.searchComments(searchCriteria);
                        displayComments(searchResults);*/
                        break;

                    case 4:
                        int commentIdToUpdate = UIUtil.getIntInput(scanner, "Enter Comment ID to update: ");
                        Comment updatedComment = captureCommentDetails(scanner);
                        updatedComment.setCommentId(commentIdToUpdate);
                        commentManager.updateComment(updatedComment);
                        System.out.println("Comment updated successfully.");
                        break;

                    case 5:
                        int commentIdToDelete = UIUtil.getIntInput(scanner, "Enter Comment ID to delete: ");
                        commentManager.deleteComment(commentIdToDelete);
                        System.out.println("Comment deleted successfully.");
                        break;

                    case 6:
                        System.out.println("Closing connection to DB ...");
                        commentManager.closeDatabaseConnection();
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
    }

    private static Comment captureCommentDetails(Scanner scanner) {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Comment: ");
        String commentText = scanner.nextLine();
        System.out.print("Enter Stars: ");
        int stars = scanner.nextInt();

        Comment newComment = new Comment();
        newComment.setBookId(bookId);
        newComment.setComment(commentText);
        newComment.setStars(stars);

        return newComment;
    }

    private static void displayComments(List<Comment> comments) {
        System.out.println("Comments:");
        for (Comment comment : comments) {
            CommentViewModel viewModel = new CommentViewModel(comment);
            System.out.println(viewModel);
        }
    }
}
