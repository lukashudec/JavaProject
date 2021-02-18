package E2ETests.Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        driver.get("https://www.boardgamegeek.com/");
    }
}
