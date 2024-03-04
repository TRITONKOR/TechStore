package com.tritonkor.techstore.ui;

import com.tritonkor.techstore.domain.contracts.AuthService;
import com.tritonkor.techstore.domain.contracts.ReviewService;
import com.tritonkor.techstore.domain.contracts.TechniqueService;
import com.tritonkor.techstore.domain.dto.ReviewAddDTO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Grade;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import com.tritonkor.techstore.persistence.entity.impl.Technique;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * The {@code MainMenuView} class represents the main menu of the application, providing options for
 * viewing and leaving reviews.
 */
public class MainMenuView implements Renderable{

    private final ReviewService reviewService;
    private final AuthService authService;
    private final TechniqueService techniqueService;
    private Client currentClient;

    /**
     * Constructs a new instance of MainMenuView.
     *
     * @param reviewService    The review service.
     * @param authService      The authentication service.
     * @param techniqueService The technique service.
     */
    public MainMenuView(ReviewService reviewService, AuthService authService, TechniqueService techniqueService) {
        this.reviewService = reviewService;
        this.authService = authService;
        this.techniqueService = techniqueService;
    }

    /**
     * Renders the main menu, allowing users to view reviews, leave reviews, or exit the application.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void render() throws IOException {
        currentClient = authService.getClient();

        while (true) {
            showMenu();
            int choise = Integer.parseInt(ConsoleTools.getUserText());
            switch (choise) {
                case 1:
                    reviewsShow();
                    break;
                case 2:
                    reviewCreateShow();
                    break;
                case 3:
                    ConsoleTools.clearConsole();
                    return;
                default:
                    System.out.println("Wrong choice, try again");
            }
        }
    }

    /**
     * Displays the main menu options.
     */
    @Override
    public void showMenu() {
        System.out.println("Choose what you want:");
        System.out.println("1. Show reviews");
        System.out.println("2. Leave a review");
        System.out.println("3. Exit");
        System.out.print("Your choose:");
    }

    /**
     * Displays the list of techniques to choose from when leaving a review.
     *
     * @param techniques The list of techniques.
     */
    private void showTechniquesList(List<Technique> techniques) {
        int i = 1;
        for (Technique technique : techniques) {
            System.out.println(i + ") " + technique);
            i++;
        }
    }

    /**
     * Displays the form for creating a new review, prompts the user for input, and processes the
     * review creation.
     *
     * @throws IOException if an I/O error occurs.
     */
    private void reviewCreateShow() throws IOException {
        List<Technique> techniques = techniqueService.getAll();

        ConsoleTools.clearConsole();
        System.out.println("Choose technique:");

        showTechniquesList(techniques);
        int techniqueIndex;
        while (true) {
            try {
                techniqueIndex = Integer.parseInt(ConsoleTools.getUserText());
                if (techniqueIndex < -1 || techniqueIndex > techniques.size()) {
                    System.out.println("Wrong index, try again");
                    continue;
                }
                break;
            }catch (NumberFormatException e) {
                System.out.println("Wrong number format. Please, enter number.");
            }
        }

        System.out.println("Now write text for review: ");
        String text = ConsoleTools.getUserText();

        System.out.println("Now choose grade from 1 to 10: ");
        Grade reviewGrade;
        while (true) {
            try {
                int grade = Integer.parseInt(ConsoleTools.getUserText());
                reviewGrade = new Grade(grade);
                break;
            }catch (NumberFormatException e) {
                System.out.println("Wrong number format. Please, enter number.");
            }
        }

        ReviewAddDTO reviewAddDTO = new ReviewAddDTO(UUID.randomUUID(), currentClient, techniques.get(techniqueIndex - 1), text, reviewGrade, LocalDateTime.now().truncatedTo(
                ChronoUnit.MINUTES));
        ConsoleTools.clearConsole();
        System.out.println("Review has been created");
        reviewService.add(reviewAddDTO);
    }

    /**
     * Displays the list of reviews based on the user's choice (all reviews or only the user's reviews).
     *
     * @throws IOException if an I/O error occurs.
     */

    private void reviewsShow() throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Review> reviews;

        System.out.println("Which filter choose for search?");
        System.out.println("1. Show all");
        System.out.println("2. Show only mine");
        System.out.println("Your choose: ");
        int choise = Integer.parseInt(ConsoleTools.getUserText());
        while (true) {
            switch (choise) {
                case 1:
                    reviews = reviewService.getAll();
                    for (Review review : reviews) {
                        System.out.println(review);
                    }
                    System.out.println("Press Enter for leave");
                    scanner.nextLine();

                    ConsoleTools.clearConsole();
                    return;
                case 2:
                    reviews = reviewService.findAllByClient(currentClient);
                    for (Review review : reviews) {
                        System.out.println(review);
                    }
                    System.out.println("Press Enter for leave");
                    scanner.nextLine();

                    ConsoleTools.clearConsole();
                    return;
            }
        }
    }
}
