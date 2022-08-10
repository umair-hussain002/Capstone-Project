package Testsuites.UITestSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Applications.ParaBankMain;
import Applications.ParabankApplicaiton.Models.Credentials;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class TransferUITests {
    public WebDriver _driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        _driver = new ChromeDriver();
        _driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        _driver.manage().window().maximize();
        given().post("https://parabank.parasoft.com/parabank/services/bank/initializeDB")
                .then().statusCode(204);
    }

    @AfterMethod
    public void tearDown(){
        _driver.close();
        _driver.quit();
    }

    @Test
    public void Transfer100Dollars() {
        ParaBankMain paraBank = new ParaBankMain(_driver);
        paraBank.loginPage.login(new Credentials("john", "demo"),"https://parabank.parasoft.com/parabank/index.htm");
        String Message = paraBank.accountServices.TransferFundsAndGetMessage("100", "12345", "12456");
        Assert.assertEquals(Message,"Transfer Complete!");
    }
    @Test
    public void Transfer500Dollars()  {
        ParaBankMain paraBank = new ParaBankMain(_driver);
        paraBank.loginPage.login(new Credentials("john", "demo"),"https://parabank.parasoft.com/parabank/index.htm");
        String Message = paraBank.accountServices.TransferFundsAndGetMessage("500", "12456", "12567");
        Assert.assertEquals(Message,"Transfer Complete!");
    }
    @Test
    public void Transfer100000Dollars() {
        ParaBankMain paraBank = new ParaBankMain(_driver);
        paraBank.loginPage.login(new Credentials("john", "demo"),"https://parabank.parasoft.com/parabank/index.htm");
        String Message = paraBank.accountServices.TransferFundsAndGetMessage("1000000", "12345", "12456");
        Assert.assertEquals(Message,"Transfer Complete!");
    }
}
