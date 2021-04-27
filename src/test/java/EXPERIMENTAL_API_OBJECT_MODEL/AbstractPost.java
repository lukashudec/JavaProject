package EXPERIMENTAL_API_OBJECT_MODEL;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AbstractPost {
    public Response response;

    String ENDPOINT;
    String URL;

    public AbstractPost(String ENDPOINT, String URL) {
        this.ENDPOINT = ENDPOINT;
        this.URL = URL;
    }

    public Response post(HashMap<String,String> requestBody) {
        response = RestAssured
                .given().baseUri(ENDPOINT).basePath(URL)
                .contentType("application/json")
                .body(requestBody)
                .post();
        return response;
    }

}
