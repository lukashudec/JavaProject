package utilityClasses.utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class ManagedDriver {
    private WebDriver driver;


        public WebDriver getDriver() {
            return driver;
        }

        @Before
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", resourceMapping.pathToChromedriver);
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        }

        @After
        public void tearDown() {
            driver.close();driver.quit();

        }
}


