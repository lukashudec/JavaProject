package cucumberE2ETests.api.implementation;

import io.restassured.response.Response;

public interface PATCH {
    Response patch(String input);
}
