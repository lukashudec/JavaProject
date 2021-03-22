package JUnitE2ETests;

import cucumberE2ETests.pages.MainPage;
import cucumberE2ETests.pages.FaqPage;
import cucumberE2ETests.pages.GeekSearchResultPage;
import cucumberE2ETests.pages.SignInPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class E2ETest {
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
        new MainPage(driver)
                .visit()
                .searchGames("Prophecy")
                .isGameImageFound("Prophecy")
                .isGameLinkFound("Prophecy");
    }

    @Test
    void testSignIn() {
/*
    Given I am on the main page
    When I click on Sign in button
    Then popup is shown
    And it contains field username
    And it contains field password
    When I enter name and pass */
        new MainPage(driver)
                .visit()
                .clickOnSignIn()
                .isFormDisplayed()
                .isPasswordDisplayed()
                .isUsernameDisplayed()
                .signIn("name", "pass");
    }

    @Test
    void testHelpSearach() {
/*
    Given I am on the FAQ page
    Then search box is present
    And BoardGameGeek FAQ article is present
    When I search for API
    Then List of results with BGG_XML_API2 is shown */
        new FaqPage(driver).visit()
                .isSearchDisplayed()
                .isArticleDisplayed()
                .search("API")
                .isResultFound("BGG_XML_API2");
    }


}
