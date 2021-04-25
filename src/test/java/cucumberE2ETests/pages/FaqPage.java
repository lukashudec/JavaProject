package cucumberE2ETests.pages;

import utilityClasses.utility.ManagedDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaqPage extends BasePage {

    @FindBy(id = "wiki-search")
    protected WebElement helpSearch;
    @FindBy(name = "B1")
    protected WebElement helpSearchButton;
    @FindBy(xpath = "//table[@class='forum_table']")
    protected WebElement forumTable;
    @FindBy(xpath = "//a[@href='/wiki/page/BoardGameGeek_FAQ']")
    protected WebElement faqArticle;

    public FaqPage(ManagedDriver managedDriver) {
        super(managedDriver);
        root = "https://www.boardgamegeek.com/wiki/page/BoardGameGeek_FAQ";
    }

    public FaqPage visit() {
        managedDriver.getDriver().get(root);
        return this;
    }

    @Given("I am on the FAQ page")
    public FaqPage openFAQPage() {
        return visit();
    }

    @When("I search for {}")
    public FaqPage search(String input) {
        helpSearch.sendKeys(input + Keys.ENTER);
        return this;
    }

    public WebElement checkResultTable(String searchResult) {
        return findElement(By.xpath("//a[@href='/wiki/page/" + searchResult + "']"));
    }

    @Then("search box is present")
    public FaqPage isSearchDisplayed() {
        assertTrue(helpSearch.isDisplayed());
        return this;
    }

    @Then("BoardGameGeek FAQ article is present")
    public FaqPage isArticleDisplayed() {
        assertNotNull(faqArticle);
        return this;
    }

    @Then("List of results with {} is shown")
    public FaqPage isResultFound(String searchResult) {
        assertNotNull(checkResultTable(searchResult));
        return this;
    }
}
