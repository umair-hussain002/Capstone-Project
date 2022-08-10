package testsuites.uiTestsSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import applications.ParaBankMain;
import applications.parabankApplication.models.Credentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TransferUITests {
    public WebDriver _driver;
    Properties prop = new Properties();

    @BeforeMethod
    public void setup() throws IOException {
        FileInputStream fs = new FileInputStream("config.properties");
        prop.load(fs);
        WebDriverManager.chromedriver().setup();
        _driver = new ChromeDriver();
        _driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        _driver.manage().window().maximize();
        given().post(prop.getProperty("ParabankServiceURL") + "/initializeDB")
                .then().statusCode(204);
    }

    @AfterMethod
    public void tearDown() {
        _driver.close();
        _driver.quit();
    }

    @Test
    public void Test1_Transfer100Dollars_fromUI() {
        ParaBankMain paraBank = new ParaBankMain(_driver);
        paraBank.loginPage.login(new Credentials(prop.getProperty("UserName"), prop.getProperty("Password")), prop.getProperty("ParabankHomePage"));
        String Message = paraBank.accountServices.TransferFundsAndGetMessage(prop.getProperty("SmallAmount"), prop.getProperty("Account1"), prop.getProperty("Account2"));
        Assert.assertEquals(Message, "Transfer Complete!");
    }

    @Test
    public void Test2_Transfer500Dollars_fromUI() {
        ParaBankMain paraBank = new ParaBankMain(_driver);
        paraBank.loginPage.login(new Credentials(prop.getProperty("UserName"), prop.getProperty("Password")), prop.getProperty("ParabankHomePage"));
        String Message = paraBank.accountServices.TransferFundsAndGetMessage(prop.getProperty("MediumAmount"), prop.getProperty("Account2"), prop.getProperty("Account3"));
        Assert.assertEquals(Message, "Transfer Complete!");
    }

    @Test
    public void Test3_Transfer100000Dollars_fromUI() {
        ParaBankMain paraBank = new ParaBankMain(_driver);
        paraBank.loginPage.login(new Credentials(prop.getProperty("UserName"), prop.getProperty("Password")), prop.getProperty("ParabankHomePage"));
        String Message = paraBank.accountServices.TransferFundsAndGetMessage(prop.getProperty("BigAmount"), prop.getProperty("Account1"), prop.getProperty("Account2"));
        Assert.assertEquals(Message, "Transfer Complete!");
    }
}
