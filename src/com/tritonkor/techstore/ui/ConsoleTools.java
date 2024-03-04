package com.tritonkor.techstore.ui;

import static java.lang.System.in;

import java.util.Scanner;

/**
 * The {@code ConsoleTools} class provides utility methods for interacting with the console.
 */
public class ConsoleTools {

    private static Scanner scanner = new Scanner(in);

    /**
     * Clears the console screen.
     */
    public static void clearConsole() {
        System.out.print("\033[H\033[2J"); // ANSI escape sequence for clearing the console
        System.out.flush();

        System.out.println(
                "                                          $$\\           $$\\                           $$\\       \n"
                        + "                                          $$ |          $$ |                          $$ |      \n"
                        + " $$$$$$\\  $$\\   $$\\  $$$$$$\\   $$$$$$$\\ $$$$$$\\         $$$$$$$\\   $$$$$$\\   $$$$$$\\  $$ |  $$\\ \n"
                        + "$$  __$$\\ $$ |  $$ |$$  __$$\\ $$  _____|\\_$$  _|        $$  __$$\\ $$  __$$\\ $$  __$$\\ $$ | $$  |\n"
                        + "$$ /  $$ |$$ |  $$ |$$$$$$$$ |\\$$$$$$\\    $$ |          $$ |  $$ |$$ /  $$ |$$ /  $$ |$$$$$$  / \n"
                        + "$$ |  $$ |$$ |  $$ |$$   ____| \\____$$\\   $$ |$$\\       $$ |  $$ |$$ |  $$ |$$ |  $$ |$$  _$$<  \n"
                        + "\\$$$$$$$ |\\$$$$$$  |\\$$$$$$$\\ $$$$$$$  |  \\$$$$  |      $$$$$$$  |\\$$$$$$  |\\$$$$$$  |$$ | \\$$\\ \n"
                        + " \\____$$ | \\______/  \\_______|\\_______/    \\____/       \\_______/  \\______/  \\______/ \\__|  \\__|\n"
                        + "$$\\   $$ |                                                                                      \n"
                        + "\\$$$$$$  |                                                                                      \n"
                        + " \\______/");
    }

    /**
     * Gets user input from the console.
     *
     * @return The user input as a String.
     */
    public static String getUserText() {
        return scanner.nextLine();
    }
}
