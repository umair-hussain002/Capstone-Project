package APITests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import parabank.pages.models.Credentials;
import parabank.pages.models.TransferRequest;
import parabank.pages.webServices.Transfers;

import static io.restassured.RestAssured.given;

public class TransferAPITests {

    @BeforeTest
    public void setup (){
        given().post("https://parabank.parasoft.com/parabank/services/bank/initializeDB")
                .then().statusCode(204);
    }

    @Test
    public void Transfer100Dollars() {
        Transfers transfers = new Transfers();
        Credentials credentials = new Credentials("john", "demo");
        TransferRequest transferRequest = new TransferRequest("12345","12456","100");
        var response = transfers.Transfer(credentials,transferRequest);
        System.out.println(response);
        String[] parts = response.split(" ");
        Assert.assertEquals(parts[2],"$100");
        Assert.assertEquals(parts[5],"#12345");
        Assert.assertEquals(parts[8],"#12456");

    }
    @Test
    public void Transfer500Dollars()  {
        Transfers transfers = new Transfers();
        Credentials credentials = new Credentials("john", "demo");
        TransferRequest transferRequest = new TransferRequest("12456","12567","500");
        var response = transfers.Transfer(credentials,transferRequest);
        String[] parts = response.split(" ");
        Assert.assertEquals(parts[2],"$500");
        Assert.assertEquals(parts[5],"#12456");
        Assert.assertEquals(parts[8],"#12567");

    }
    @Test
    public void Transfer100000Dollars() {
        Transfers transfers = new Transfers();
        Credentials credentials = new Credentials("john", "demo");
        TransferRequest transferRequest = new TransferRequest("12345","12456","1000000");
        var response = transfers.Transfer(credentials,transferRequest);
        String[] parts = response.split(" ");
        Assert.assertEquals(parts[2],"$1000000");
        Assert.assertEquals(parts[5],"#12345");
        Assert.assertEquals(parts[8],"#12456");

    }
}
