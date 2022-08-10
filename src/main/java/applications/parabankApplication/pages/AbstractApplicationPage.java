package applications.parabankApplication.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public abstract class AbstractApplicationPage {
    private WebDriver _driver;

    public AbstractApplicationPage(WebDriver driver) {
        this._driver = driver;
    }

    public void navigate(String url) {
        _driver.navigate().to(url);
    }

    //find an element
    public WebElement findOne(By by) {
        WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    //click
    public void click(By by) {
        findOne(by).click();
    }

    //send keys
    public void sendKeys(By by, String text) {
        WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        findOne(by).sendKeys(text);
    }

    //select from dropdown
    public void selectFromDropdown(By by, String text) {
        var select = new Select(findOne(by));
        select.selectByVisibleText(text);
    }

    public String getText(By by) {
        return findOne(by).getText();
    }
}
