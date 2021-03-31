package cucumberE2ETests.utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class ManagedDriver extends ChromeDriver {



    public WebDriver getDriver() {
        return this;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/lenovo/Downloads/chromedriver_win32_89/chromedriver.exe");
        manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        close();
        quit();
    }
}
