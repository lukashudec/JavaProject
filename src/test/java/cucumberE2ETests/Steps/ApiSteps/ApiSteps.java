package cucumberE2ETests.Steps.ApiSteps;

import cucumberE2ETests.api.typicodeAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ApiSteps {
    typicodeAPI api = new typicodeAPI();

    @When("I send post request with title:{}, body:{}, userId:{}")
    public void iSendPostRequest(String title, String body, String userId) {
        api.post(title, body, userId);
    }

    @When("I request post with id:{}")
    public void iRequestPostWithIdPost_id(String input) {
        api.get(input);
    }

    @Then("response status is {}")
    public void responseStatusIsStatus(String input) {
        api.responseStatusIs(input);
    }

    @And("response key:{} contains {}")
    public void responseContainsKeyEqualTo(String key, String input) {
        api.responseContainsKeyEqualTo(key,input);
    }
}
