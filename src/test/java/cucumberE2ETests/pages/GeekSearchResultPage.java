package cucumberE2ETests.pages;


import utilityClasses.utility.ManagedDriver;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GeekSearchResultPage extends BasePage {

    public GeekSearchResultPage(ManagedDriver managedDriver) {
        super(managedDriver);
    }

    public List<WebElement> getGameLink(String input) {
        return managedDriver.findElements(By.linkText(input));
    }

    public List<WebElement> getGameImage(String input) {
        return managedDriver.findElements(By.xpath("//img[@alt='Board Game: " + input + "']"));
    }

    @Then("Search results for link: {} should appear")
    @Step
    public GeekSearchResultPage isGameLinkFound(String input) {
        assertNotEquals(0, getGameLink(input).size());
        return this;
    }

    @Then("Search results for image: {} should appear")
    @Step
    public GeekSearchResultPage isGameImageFound(String input) {
        assertNotEquals(0, getGameImage(input).size());
        return this;
    }
}
