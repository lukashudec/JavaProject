package appiumTests.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

class AppiumClassTest {

    static AppiumDriver<MobileElement> driver;
    static AppiumDriverLocalService server;
    static Process process;

    @BeforeAll
    public static void startEmulatorAndAppium() throws InterruptedException, IOException {
        process = new ProcessBuilder("C:/Users/lenovo/AppData/Local/Android/Sdk/emulator/emulator.exe", "-avd", "Gala_11", "no-snapshot-save").start();
        process.waitFor(5, TimeUnit.SECONDS);


        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withEnvironment(environment);

        server = AppiumDriverLocalService.buildService(serviceBuilder);

        if (process.isAlive()) {
         server.start(); }
    }

    @AfterAll
    public static void stopEmulatorAndAppium() {
        server.stop();
        process.destroy();
    }


    @BeforeEach
    public void setUp()  {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        driver = new AppiumDriver<>(server.getUrl(), caps);
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
