package cucumberE2ETests.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class typicodeAPI {
    String API_ENDPOINT = "https://jsonplaceholder.typicode.com";
    String GET_POSTS = "/posts/";
    String POST_POSTS = "/posts/";
    public Response response;
    public FilterableRequestSpecification request = (FilterableRequestSpecification) new RequestSpecBuilder().build();

    @When("Experimental request post with id:{}")
    public Response get(String input) {
        request.baseUri(API_ENDPOINT)
                .basePath(GET_POSTS);
        response = RestAssured
                .given(request)
                .get(input);
        return response;
    }

    public Response post(String title, String body, String userId) {
        Map<String, String> reqBody = new HashMap<>();
        reqBody.put("title", title);
        reqBody.put("body", body);
        reqBody.put("userID", userId);
        request.baseUri(API_ENDPOINT)
                .basePath(POST_POSTS)
                .contentType("application/json")
                .body(reqBody);
        response = RestAssured
                .given(request).post();
        return response;
    }

    public boolean getValueFromBody(String key, String input) {
        return response.getBody().jsonPath()
                .getString(key).contains(input);
    }

    @Then("Experimental status is {}")
    public typicodeAPI responseStatusIs(String input) {
        assertEquals(Integer.parseInt(input), response.getStatusCode());
        return this;
    }

    public typicodeAPI responseContainsKeyEqualTo(String key, String input) {
        assertNotNull(response.getBody().jsonPath().getString(key));
        assertTrue(getValueFromBody(key, input));
        return this;
    }
}
