package Applications.ParabankApplicaiton.Services.WebServices;

import Applications.ParabankApplicaiton.Models.Credentials;
import Applications.ParabankApplicaiton.Models.TransferRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Transfers {

    String baseURL = "https://parabank.parasoft.com/parabank/services/bank/";
    public ObjectMapper mapper = new ObjectMapper();

    public String Transfer(Credentials credentials, TransferRequest transferRequest) {
        Map<String, Object> params = mapper.convertValue(transferRequest, Map.class);
        var response = given()
                .auth()
                .basic(credentials.username, credentials.password)
                .queryParams(params)
                .log().all()
                .post(baseURL+"transfer");
        response.then().statusCode(200);
        return response.body().asString();
    }
}
