package com.tritonkor.techstore.ui;

import com.tritonkor.techstore.domain.contracts.AuthService;
import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.contracts.SignUpService;
import com.tritonkor.techstore.domain.exception.AuthException;
import java.io.IOException;

/**
 * The {@code AuthMenuView} class represents the user interface for authentication-related actions.
 * It allows users to sign up, log in, and exit the application.
 */
public class AuthMenuView implements Renderable{

    private final ClientService clientService;
    private final SignUpService signUpService;
    private final AuthService authService;

    private final MainMenuView mainMenuView;

    /**
     * Constructs a new instance of AuthMenuView.
     *
     * @param clientService The client service.
     * @param authService   The authentication service.
     * @param signUpService The sign-up service.
     * @param mainMenuView  The main menu view.
     */
    public AuthMenuView(ClientService clientService, AuthService authService, SignUpService signUpService, MainMenuView mainMenuView) {
        this.clientService = clientService;
        this.signUpService = signUpService;
        this.authService = authService;
        this.mainMenuView = mainMenuView;
    }

    /**
     * Renders the authentication menu, allowing users to sign up, log in, or exit the application.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void render() throws IOException {
        while (true) {
            showMenu();
            int choise = Integer.parseInt(ConsoleTools.getUserText());

            switch (choise) {
                case 1:
                    signUpShow();
                    break;

                case 2:
                    logInShow();
                    break;
                case 3:
                    ConsoleTools.clearConsole();
                    System.out.println("Good bye, friend \uD83E\uDD7A\uD83D\uDC49\uD83D\uDC48");
                    return;
                default:
                    System.out.println("Wrong choice, try again");
            }
        }
    }

    /**
     * Displays the authentication menu options.
     */
    @Override
    public void showMenu() {
        System.out.println("Choose what you want:");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");
        System.out.println("3. Exit");
        System.out.print("Your choose:");
    }

    /**
     * Displays the sign-up form, prompts the client for input, and processes the sign-up operation.
     *
     * @throws IOException if an I/O error occurs.
     */
    private void signUpShow() throws IOException{
        System.out.println("Enter your username: ");
        String username = ConsoleTools.getUserText();

        System.out.println("Enter your password: ");
        String password = ConsoleTools.getUserText();

        if(!signUpService.checkUsernameAvailability(username)) {
            ConsoleTools.clearConsole();
            System.out.println("Username is already used");
            return;
        }

        ConsoleTools.clearConsole();
        signUpService.signUp(clientService.createClient(username, password));
        authService.authenticate(username, password);
        mainMenuView.render();
    }

    /**
     * Displays the log-in form, prompts the client for input, and processes the log-in operation.
     *
     * @throws IOException if an I/O error occurs.
     */
    private void logInShow() throws IOException{
        System.out.println("Enter your username: ");
        String username = ConsoleTools.getUserText();

        System.out.println("Enter your password: ");
        String password = ConsoleTools.getUserText();

        try {
            authService.authenticate(username, password);
        }catch (AuthException e) {
            ConsoleTools.clearConsole();
            System.out.println("Wrong username or password");
            return;
        }

        ConsoleTools.clearConsole();
        mainMenuView.render();
    }
}
