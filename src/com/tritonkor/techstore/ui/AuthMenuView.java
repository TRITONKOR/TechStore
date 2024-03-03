package com.tritonkor.techstore.ui;

import com.tritonkor.techstore.domain.contracts.AuthService;
import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.contracts.SignUpService;
import com.tritonkor.techstore.domain.exception.AuthException;
import java.io.IOException;

public class AuthMenuView implements Renderable{

    private final ClientService clientService;
    private final SignUpService signUpService;
    private final AuthService authService;

    private final MainMenuView mainMenuView;

    public AuthMenuView(ClientService clientService, AuthService authService, SignUpService signUpService, MainMenuView mainMenuView) {
        this.clientService = clientService;
        this.signUpService = signUpService;
        this.authService = authService;
        this.mainMenuView = mainMenuView;
    }

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

    @Override
    public void showMenu() {
        System.out.println("Choose what you want:");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");
        System.out.println("3. Exit");
        System.out.print("Your choose:");
    }

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
