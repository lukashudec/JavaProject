package cucumberE2ETests.Steps.WebSteps;

import cucumberE2ETests.pages.*;
import cucumberE2ETests.utility.Entry;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

public class WebSteps {

    WebDriver driver = null;

    @Before
    public void beforeStep() {
        System.setProperty("webdriver.chrome.driver","C:/Users/lenovo/Downloads/chromedriver_win32_87/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }
    @After
    public void afterStep() {
        driver.quit();

    }

    @Given("I am on the {} page")
    public void iAmOnTheHomepage(String input) {
        if (input.contentEquals("home")) {
        MainPage page = new MainPage(driver);
        page.go();}
        if (input.contentEquals("FAQ")) {
            FaqPage page = new FaqPage(driver);
            page.go();}

    }

    @When("I enter search term: {}")
    public void iEnterSearchTermGame_name(String search) {
        MainPage page = new MainPage(driver);
        page.search(search);
    }

    @Then("Search results for link: {} should appear")
    public void searchResultsForLink_textGame_nameShouldAppear(String input) {
        GeekSearchResultPage page = new GeekSearchResultPage(driver);
        assertNotEquals(0, page.getGameLink(input).size());
        
    }

    @And("Search results for image: {} should appear")
    public void searchResultsForXpathImgAltBoardGameGame_nameShouldAppear(String input) {
        GeekSearchResultPage page = new GeekSearchResultPage(driver);
        assertNotEquals(0, page.getGameLink(input).size());
    }

    @When("I click on Sign in button")
    public void iClickOnSignInButton() {
        MainPage page = new MainPage(driver);
        page.clickOnSignIn();
    }

    @Then("popup is shown")
    public void popupIsShown() {
        SignInPage page = new SignInPage(driver);
        assertNotNull(page.loginForm);
        
    }

    @And("it contains field {}")
    public void itContainsFieldUsername(String field) {
        SignInPage page = new SignInPage(driver);
        if (field.contentEquals("username")) {
        assertNotNull(page.username); }
        if (field.contentEquals("password")) {
            assertNotNull(page.password); }
    }


    @Then("nothing")
    public void nothing() {
        assertEquals(0,0);
    }

    @When("I enter username and password")
    public void iEnterUsernameAndPassword(DataTable table) {
        Entry entry = new Entry(table, true);
        SignInPage page = new SignInPage(driver);
        page.signIn(entry.getRow(0).get(0), entry.getRow(0).get(1) );
    }

    @Then("search box is present")
    public void searchBoxIsPresent() {
        FaqPage page = new FaqPage(driver);
        assertNotNull(page.helpSearch);
    }

    @And("BoardGameGeek FAQ article is present")
    public void boardgamegeekFAQArticleIsPresent() {
        FaqPage page = new FaqPage(driver);
        assertNotNull(page.faqArticle);
    }

    @When("I search for {}")
    public void iSearchForSearch_option(String input) {
        FaqPage page = new FaqPage(driver);
        page.helpSearch.sendKeys(input + Keys.ENTER);
    }

    @Then("List of results with {} is shown")
    public void listOfResultsWithSearch_resultIsShown(String input) {
        FaqPage page = new FaqPage(driver);
        page.checkResultTable(input);
    }

}
