package cucumberE2ETests.api.implementation;

import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractAPI implements PATCH {
    public Response response;
    String url;
    String endpoint;

    public AbstractAPI responseContainsKeyEqualTo(String key, String input) {
        assertNotNull(response.getBody().jsonPath().getString(key));
        assertTrue(response.getBody().jsonPath().getString(key).contains(input));
        return this;
    }

    public AbstractAPI responseStatusIs(String input) {
        return responseStatusIs(Integer.parseInt(input)); }

    public AbstractAPI responseStatusIs(Integer input) {
        assertEquals(input, response.getStatusCode());
        return this; }

    @Override
    public Response patch(String input) {
        return null;
    }
}
