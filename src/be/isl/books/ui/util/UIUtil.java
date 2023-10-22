package be.isl.books.ui.util;

import java.util.Scanner;

public class UIUtil {
    // helpers with prompt questions
    public static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
