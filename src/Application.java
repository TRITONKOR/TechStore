import com.tritonkor.techstore.domain.contracts.AuthService;
import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.contracts.ReviewService;
import com.tritonkor.techstore.domain.contracts.SignUpService;
import com.tritonkor.techstore.domain.contracts.TechniqueService;
import com.tritonkor.techstore.domain.impl.ServiceFactory;
import com.tritonkor.techstore.persistence.dao.DAOFactory;
import com.tritonkor.techstore.ui.AuthMenuView;
import com.tritonkor.techstore.ui.ConsoleTools;
import com.tritonkor.techstore.ui.MainMenuView;
import java.io.IOException;

public final class Application {

    public static DAOFactory jsonDAOFactory;

    static void init() {
        jsonDAOFactory = DAOFactory.getDAOFactory();

        ServiceFactory serviceFactory = ServiceFactory.getInstance(jsonDAOFactory);

        AuthService authService = serviceFactory.getAuthService();
        SignUpService signUpService = serviceFactory.getSignUpService();

        ClientService clientService = serviceFactory.getClientService();
        TechniqueService techniqueService = serviceFactory.getTechniqueService();
        ReviewService reviewService = serviceFactory.getReviewService();

        ConsoleTools.clearConsole();

        try {
            MainMenuView mainMenuView = new MainMenuView(reviewService, authService, techniqueService);
            AuthMenuView authMenuView = new AuthMenuView(clientService, authService, signUpService,
                    mainMenuView);

            authMenuView.render();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
