package appiumTests.tests;

import appiumTests.tests.pages.MainPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class AppiumClassTest {

    static AppiumDriver<MobileElement> driver;
    static AppiumDriverLocalService server;
    static Process process;


    @BeforeClass
    public static void setUp() throws IOException {
        process = new ProcessBuilder("C:/Users/lenovo/AppData/Local/Android/Sdk/emulator/emulator.exe", "-avd", "Gala_11", "-no-snapshot-save")
                .start();

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_LEVEL,"error");
        server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability("skipDeviceInitialization", true);
        caps.setCapability("skipServerInstallation", true);
        caps.setCapability("appPackage", "com.google.android.deskclock");
        caps.setCapability("appActivity", "com.android.deskclock.DeskClock");
        driver = new AppiumDriver<>(server.getUrl(), caps);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.resetApp();

    }

    @AfterClass
    public static void close() throws InterruptedException {
        driver.quit();
        server.stop();
        process.children().forEach(ProcessHandle::destroy);
        process.destroyForcibly().waitFor();
    }

    @Test
    public void testAppium() {
        // this test can be remade and addted to CreateDelete -> CreateDeleteEdit to save additional time on test running
        new MainPage(driver)
                .visit()
                .goToAlarm()
                .expandAlarm(1)
                .clickOnDay("Monday")
                .clickOnDay("Tuesday")
                .clickOnDay("Wednesday");
        }

    @Test
    public void testCreateDeleteAlarm() {
        /* two tests combined into one
        * starting appium,emulator,driver is time consuming so it should be better to write more complex tests
        * even though writing complex tests is antipattern */
        new MainPage(driver)
                .visit()
                .goToAlarm()
                .createNewAlarm(12, 30, new String[]{"Monday", "Friday"})
                .goToAlarm()
                .deleteAlarm(1);
    }

}
