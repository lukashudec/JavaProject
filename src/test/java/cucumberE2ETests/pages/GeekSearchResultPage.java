package cucumberE2ETests.pages;


import cucumberE2ETests.utility.ManagedDriver;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GeekSearchResultPage extends BasePage {

    public GeekSearchResultPage(ManagedDriver managedDriver) {
        super(managedDriver);
    }

    public List<WebElement> getGameLink(String input) {
        return driver.findElements(By.linkText(input));
    }

    public List<WebElement> getGameImage(String input) {
        return driver.findElements(By.xpath("//img[@alt='Board Game: " + input + "']"));
    }

    @Then("Search results for link: {} should appear")
    public GeekSearchResultPage isGameLinkFound(String input) {
        assertNotEquals(0, getGameLink(input).size());
        return this;
    }

    @Then("Search results for image: {} should appear")
    public GeekSearchResultPage isGameImageFound(String input) {
        assertNotEquals(0, getGameImage(input).size());
        return this;
    }
}
