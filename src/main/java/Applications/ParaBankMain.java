package Applications;

import org.openqa.selenium.WebDriver;
import Applications.ParabankApplicaiton.Pages.AbstractApplicationPage;
import Applications.ParabankApplicaiton.Services.AccountServices;
import Applications.ParabankApplicaiton.Pages.LoginPage;

public class ParaBankMain extends AbstractApplicationPage {
    public ParaBankMain(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
        accountServices = new AccountServices(driver);
    }
    public LoginPage loginPage;
    public AccountServices accountServices;

}
