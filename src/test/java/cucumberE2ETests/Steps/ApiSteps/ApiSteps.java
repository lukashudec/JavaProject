package cucumberE2ETests.Steps.ApiSteps;

import cucumberE2ETests.api.typicodeAPI;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

public class ApiSteps {
    Response response;
    typicodeAPI api = new typicodeAPI();

    @Before
    public void beforeStep() {
    }

    @After
    public void afterStep() {
    }

    @When("I send post request")
    public void iSendPostRequest() {
        response = api.post();
    }

    @When("I request post with id:{}")
    public void iRequestPostWithIdPost_id(String input) {
        response = api.get(input);
    }

    @Then("response status is {}")
    public void responseStatusIsStatus(String input) {
        assertEquals(Integer.parseInt(input),response.getStatusCode());
    }

    @And("response key:{} contains {}")
    public void responseContainsKeyEqualTo(String key,String input) {
        assertNotNull(response.getBody().jsonPath().getString(key));
        assertTrue(api.getValueFromBody(key,input));
    }

    @Given("title:{}, body:{}, userId:{}")
    public void titleTitleBodyBodyUserIdUser_id(String title, String body, String userId) {
        api.preparePost(title,body,userId);
    }
}
