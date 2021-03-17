package cucumberE2ETests.Steps.WebSteps;

import cucumberE2ETests.pages.FaqPage;
import cucumberE2ETests.pages.GeekSearchResultPage;
import cucumberE2ETests.pages.MainPage;
import cucumberE2ETests.pages.SignInPage;
import cucumberE2ETests.utility.Entry;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebSteps {

    WebDriver driver = null;

    @Before
    public void beforeStep() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/lenovo/Downloads/chromedriver_win32_89/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void afterStep() {
        driver.quit();
    }

    @Given("I am on the {} page")
    public void iAmOnTheHomepage(String input) {
        if (input.contentEquals("main")) {
            new MainPage(driver).go();
        }
        if (input.contentEquals("FAQ")) {
            new FaqPage(driver).go();
        }
    }

    @When("I enter search term: {}")
    public void iEnterSearchTermGame_name(String search) {
        new MainPage(driver).searchGames(search);
    }

    @Then("Search results for link: {} should appear")
    public void searchResultsForLink_textGame_nameShouldAppear(String input) {
        assertNotEquals(0, new GeekSearchResultPage(driver).getGameLink(input).size());
    }

    @And("Search results for image: {} should appear")
    public void searchResultsForXpathImgAltBoardGameGame_nameShouldAppear(String input) {
        assertNotEquals(0, new GeekSearchResultPage(driver).getGameLink(input).size());
    }

    @When("I click on Sign in button")
    public void iClickOnSignInButton() {
        new MainPage(driver).clickOnSignIn();
    }

    @Then("popup is shown")
    public void popupIsShown() {
        assertTrue(new SignInPage(driver).isDisplayed());
    }

    @And("it contains field {}")
    public void itContainsFieldUsername(String field) {
        if (field.contentEquals("username")) {
            assertTrue(new SignInPage(driver).usernameDisplayed());
        }
        if (field.contentEquals("password")) {
            assertTrue(new SignInPage(driver).passwordDisplayed());
        }
    }


    @Then("nothing")
    public void nothing() {
        assertTrue(true);
    }

    @When("I enter username and password")
    public void iEnterUsernameAndPassword(DataTable table) {
        Entry entry = new Entry(table, true);
        new SignInPage(driver).signIn(entry.getRow(0).get(0), entry.getRow(0).get(1));
    }

    @Then("search box is present")
    public void searchBoxIsPresent() {
        assertTrue(new FaqPage(driver).searchDisplayed());
    }

    @And("BoardGameGeek FAQ article is present")
    public void boardGameGeekFAQArticleIsPresent() {
        assertTrue(new FaqPage(driver).articleDisplayed());
    }

    @When("I search for {}")
    public void iSearchForSearch_option(String input) {
        new FaqPage(driver).search(input);
    }

    @Then("List of results with {} is shown")
    public void listOfResultsWithSearch_resultIsShown(String input) {
        new FaqPage(driver).checkResultTable(input);
    }

}
