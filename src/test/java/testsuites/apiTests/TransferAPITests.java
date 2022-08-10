package testsuites.apiTests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import applications.parabankApplication.models.Credentials;
import applications.parabankApplication.models.TransferRequest;
import applications.parabankApplication.services.webServices.TransferService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TransferAPITests {
    Properties prop = new Properties();
    @BeforeTest
    public void setup () throws IOException {
        FileInputStream fs= new FileInputStream("config.properties");
        prop.load(fs);
        //API to reinitialize DB. This has been used because parabank is used by others as well and we need to refresh the db
        given().post(prop.getProperty("ParabankServiceURL")+"/initializeDB")
                .then().statusCode(204);
    }

    @Test
    public void Transfer100Dollars() {
        TransferService transferService = new TransferService();
        Credentials credentials = new Credentials(prop.getProperty("UserName"), prop.getProperty("Password"));
        TransferRequest transferRequest = new TransferRequest(prop.getProperty("Account1"),prop.getProperty("Account2"),prop.getProperty("SmallAmount"));
        var response = transferService.Transfer(prop.getProperty("ParabankServiceURL"), credentials,transferRequest);
        System.out.println(response);
        String[] parts = response.split(" ");
        Assert.assertEquals(parts[2],"$"+prop.getProperty("SmallAmount"));
        Assert.assertEquals(parts[5],"#"+prop.getProperty("Account1"));
        Assert.assertEquals(parts[8],"#"+prop.getProperty("Account2"));
    }

    @Test
    public void Transfer500Dollars()  {
        TransferService transferService = new TransferService();
        Credentials credentials = new Credentials(prop.getProperty("UserName"), prop.getProperty("Password"));
        TransferRequest transferRequest = new TransferRequest(prop.getProperty("Account2"),prop.getProperty("Account3"),prop.getProperty("MediumAmount"));
        var response = transferService.Transfer(prop.getProperty("ParabankServiceURL"), credentials,transferRequest);
        String[] parts = response.split(" ");
        Assert.assertEquals(parts[2],"$"+prop.getProperty("MediumAmount"));
        Assert.assertEquals(parts[5],"#"+prop.getProperty("Account2"));
        Assert.assertEquals(parts[8],"#"+prop.getProperty("Account3"));
    }

    @Test
    public void Transfer100000Dollars() {
        TransferService transferService = new TransferService();
        Credentials credentials = new Credentials(prop.getProperty("UserName"), prop.getProperty("Password"));
        TransferRequest transferRequest = new TransferRequest(prop.getProperty("Account1"),prop.getProperty("Account2"),prop.getProperty("BigAmount"));
        var response = transferService.Transfer(prop.getProperty("ParabankServiceURL"), credentials,transferRequest);
        String[] parts = response.split(" ");
        Assert.assertEquals(parts[2],"$"+prop.getProperty("BigAmount"));
        Assert.assertEquals(parts[5],"#"+prop.getProperty("Account1"));
        Assert.assertEquals(parts[8],"#"+prop.getProperty("Account2"));
    }
}
