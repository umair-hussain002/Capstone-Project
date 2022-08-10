package applications;

import org.openqa.selenium.WebDriver;
import applications.parabankApplication.pages.AbstractApplicationPage;
import applications.parabankApplication.services.AccountService;
import applications.parabankApplication.pages.LoginPage;

public class ParaBankMain extends AbstractApplicationPage {
    public ParaBankMain(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
        accountServices = new AccountService(driver);
    }

    public LoginPage loginPage;
    public AccountService accountServices;

}
