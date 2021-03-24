package appiumTests.tests;

import appiumTests.tests.pages.AlarmPage;
import appiumTests.tests.pages.MainPage;
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
    public static void startEmulatorAndAppium() {
        System.out.println("Starting emulator");
        try {
            process = new ProcessBuilder("C:/Users/lenovo/AppData/Local/Android/Sdk/emulator/emulator.exe", "-avd", "Gala_11", "-no-snapshot-save").start();
            process.waitFor(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException | IOException e) {
            System.out.println("Server goes oops");
            e.printStackTrace();
        }

        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));

        System.out.println("Starting appium");
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withEnvironment(environment);

        server = AppiumDriverLocalService.buildService(serviceBuilder);

        if (process.isAlive()) {
            System.out.println("Starting server");
         server.start(); }
    }

    @AfterAll
    public static void stopEmulatorAndAppium() {
        server.stop();
        process.destroy();
    }


    @BeforeEach
    public void setUp()  {
        System.out.println("Setting up");
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
    void testAppium() throws InterruptedException {
        MainPage page = new MainPage(driver);
        AlarmPage alarmPage = page.visit().goToAlarm();
        Thread.sleep(1500);

      /*  driver.findElementByAccessibilityId("Clock").click();

        driver.findElementByAccessibilityId("Alarm").click();
        driver.findElementByAccessibilityId("Clock").click();
        driver.findElementByAccessibilityId("Timer").click();
        driver.findElementByAccessibilityId("StopWatch").click();

        driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\" Alarm\"])[2]").click();
        driver.findElementByAccessibilityId("Monday").click();
        driver.findElementByAccessibilityId("9:00AM").click();
        driver.findElementByAccessibilityId("11").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByAccessibilityId("Collapse alarm").click();

        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\" Alarm\"])[2]/android.widget.Switch").click();
   */
    }


}
