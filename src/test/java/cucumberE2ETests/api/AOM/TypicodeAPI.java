package cucumberE2ETests.api.AOM;

import cucumberE2ETests.api.implementation.AbstractAPI;
import cucumberE2ETests.api.implementation.GET;
import cucumberE2ETests.api.implementation.PATCH;
import cucumberE2ETests.api.implementation.POST;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TypicodeAPI {
    public Response response;
    public FilterableRequestSpecification request = (FilterableRequestSpecification) new RequestSpecBuilder().build();
    String API_ENDPOINT = "https://jsonplaceholder.typicode.com";
    String GET_POSTS = "/posts/";
    String POST_POSTS = "/posts/";

    @When("I request post with id:{}")
    public Response get(String input) {
        response = RestAssured
                .given().baseUri(API_ENDPOINT).basePath(GET_POSTS)
                .get(input);
        return response;
    }

    @When("I send post request with title:{}, body:{}, userId:{}")
    public Response post(String title, String body, String userId) {
        Map<String, String> reqBody = new HashMap<>();
        reqBody.put("title", title);
        reqBody.put("body", body);
        reqBody.put("userID", userId);
        response = RestAssured
                .given().baseUri(API_ENDPOINT).basePath(POST_POSTS)
                .contentType("application/json")
                .body(reqBody)
                .post();
        return response;
    }

    @Then("response status is {}")
    public TypicodeAPI responseStatusIs(String input) {
        return responseStatusIs(Integer.parseInt(input)); }

    public TypicodeAPI responseStatusIs(Integer input) {
        assertEquals(input, response.getStatusCode());
        return this; }

    @Then("response key:{} contains {}")
    public TypicodeAPI responseContainsKeyEqualTo(String key, String value) {
        assertNotNull(response.getBody().jsonPath().getString(key));
        assertTrue(response.getBody().jsonPath().getString(key).contains(value));
        return this;
    }
}
