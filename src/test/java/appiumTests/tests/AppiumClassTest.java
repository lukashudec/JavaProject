package appiumTests.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

class AppiumClassTest {

    AppiumDriver<MobileElement> driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        driver = new AppiumDriver<>(new URL(" http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
         if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAppium() {
        driver.findElementByAccessibilityId("Clock").click();
        driver.findElementByAccessibilityId("8:30 AM").click();
        driver.findElementByAccessibilityId("12").click();
    }


}
