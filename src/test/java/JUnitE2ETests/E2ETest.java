package JUnitE2ETests;

import cucumberE2ETests.pages.FaqPage;
import cucumberE2ETests.pages.GeekSearchResultPage;
import cucumberE2ETests.pages.MainPage;
import cucumberE2ETests.pages.SignInPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class E2ETest {
    WebDriver driver = null;

    @BeforeEach
    public void beforeStep() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/lenovo/Downloads/chromedriver_win32_89/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterEach
    public void afterStep() {
        driver.quit();
    }

    @Test
    void searchForGame() {
/*
    Given I am on the home page
    When I enter search term: Prophecy
    Then Search results for link: Prophecy should appear
    And Search results for image: Prophecy should appear */
        MainPage mainPage = (MainPage) new MainPage(driver).go();
        GeekSearchResultPage searchResultPage = mainPage.searchGames("Prophecy");
        assertNotEquals(0, searchResultPage.getGameImage("Prophecy").size());
        assertNotEquals(0, searchResultPage.getGameLink("Prophecy").size());
    }

    @Test
    void testSignIn() {
/*
    Given I am on the main page
    When I click on Sign in button
    Then popup is shown
    And it contains field username
    And it contains field password
    When I enter name and pass
    Then nothing */
        MainPage mainPage = (MainPage) new MainPage(driver).go();
        SignInPage signPage = mainPage.clickOnSignIn();
        assertTrue(signPage.isDisplayed());
        assertTrue(signPage.passwordDisplayed());
        assertTrue(signPage.usernameDisplayed());
        signPage.signIn("name", "pass");
        assertTrue(true);
    }

    @Test
    void testHelpSearach() {
/*
    Given I am on the FAQ page
    Then search box is present
    And BoardGameGeek FAQ article is present
    When I search for API
    Then List of results with BGG_XML_API2 is shown */
        FaqPage faqPage = (FaqPage) new FaqPage(driver).go();
        assertTrue(faqPage.searchDisplayed());
        assertTrue(faqPage.articleDisplayed());
        faqPage.search("API");
        assertNotNull(faqPage.checkResultTable("BGG_XML_API2"));
    }


}
