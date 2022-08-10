package applications.parabankApplication.services;

import applications.parabankApplication.pages.AbstractApplicationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountService extends AbstractApplicationPage {
    private By TransferFunds = By.xpath("//a[text()=\"Transfer Funds\"]");
    private By Amount = By.id("amount");
    //private By FromAccount = By.id("fromAccountId");
    private By FromAccount = By.xpath("//select[@id='fromAccountId']");
    private By ToAccount = By.id("toAccountId");
    private By TransferBtn = By.xpath("//input[@Value='Transfer']");
    private By Message = By.xpath("//h1[text()='Transfer Complete!']");
    public AccountService(WebDriver driver) {
        super(driver);
    }

    public String TransferFundsAndGetMessage(String Amount, String FromAccount, String ToAccount) {
    click(TransferFunds);
    selectFromDropdown(this.FromAccount, FromAccount);
    selectFromDropdown(this.ToAccount,ToAccount);
    sendKeys(this.Amount,Amount);
    click(TransferBtn);
    return getText(Message);
    }
}
