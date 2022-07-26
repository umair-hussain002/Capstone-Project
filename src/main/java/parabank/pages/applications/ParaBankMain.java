package parabank.pages.applications;

import org.openqa.selenium.WebDriver;
import parabank.pages.AbstractApplicationPage;
import parabank.pages.AccountServices;
import parabank.pages.LoginPage;

public class ParaBankMain extends AbstractApplicationPage {
    public ParaBankMain(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
        accountServices = new AccountServices(driver);
    }
    public LoginPage loginPage;
    public AccountServices accountServices;

}
