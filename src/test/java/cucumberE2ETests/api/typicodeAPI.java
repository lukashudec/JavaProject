package cucumberE2ETests.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;

public class typicodeAPI {
     String API_ENDPOINT = "https://jsonplaceholder.typicode.com";
     String GET_POSTS = "/posts/";
     String POST_POSTS = "/posts/";
     Response response;
     FilterableRequestSpecification request = (FilterableRequestSpecification) new RequestSpecBuilder().build();

    public Response get(String input) {
        request.baseUri(API_ENDPOINT)
                .basePath(GET_POSTS);
        response = RestAssured
                .given(request)
                .get(input);
        return response;
    }

    public RequestSpecification preparePost(String title, String body, String userId) {
        Map<String,String> reqBody = new HashMap<>();
        reqBody.put("title",title);
        reqBody.put("body",body);
        reqBody.put("userID",userId);
        request.baseUri(API_ENDPOINT)
                .basePath(POST_POSTS)
                .contentType("application/json")
                .body(reqBody);
        return request;
    }

    public Response post() {
        response = RestAssured
                .given(request).post();
        return response;
    }

    public boolean getValueFromBody(String key,String input) {
        return response.getBody().jsonPath()
                .getString(key).contains(input);
    }
}
