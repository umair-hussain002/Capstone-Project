package parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import parabank.pages.models.Credentials;

public class LoginPage extends AbstractApplicationPage {
    private By username = By.name("username");
    private By password= By.name("password");
    private By loginBtn = By.xpath("//input[@value='Log In']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login (Credentials credentials, String url){
        navigate(url);
        sendKeys(username,credentials.username);
        sendKeys(password, credentials.password);
        click(loginBtn);

    }
}
