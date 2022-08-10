package applications.parabankApplication.services.webServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import applications.parabankApplication.models.Credentials;
import applications.parabankApplication.models.TransferRequest;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TransferService {

    public ObjectMapper mapper = new ObjectMapper();

    public String Transfer(String url, Credentials credentials, TransferRequest transferRequest) {
        Map<String, Object> params = mapper.convertValue(transferRequest, Map.class);
        var response = given()
                .auth()
                .basic(credentials.username, credentials.password)
                .queryParams(params)
                .log().all()
                .post(url + "/transfer");
        response.then().statusCode(200);
        return response.body().asString();
    }
}
