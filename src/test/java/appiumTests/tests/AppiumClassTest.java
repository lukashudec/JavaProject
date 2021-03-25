package appiumTests.tests;

import appiumTests.tests.pages.MainPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
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

    public static void startEmulator() throws IOException {
        process = new ProcessBuilder("C:/Users/lenovo/AppData/Local/Android/Sdk/emulator/emulator.exe", "-avd", "Gala_11", "-no-snapshot-save").start();
    }

    @BeforeAll
    public static void startEmulatorAndAppium() throws IOException {
        startEmulator();

        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withEnvironment(environment)
                .withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();
    }

    @AfterAll
    public static void stopEmulatorAndAppium() throws InterruptedException {
        server.stop();
        process.children().forEach(ProcessHandle::destroy);
        process.destroyForcibly().waitFor();
    }


    @BeforeEach
    public void setUp() throws IOException {
        if (!process.isAlive()) {
            System.out.println("Is alive");
            startEmulator();
            server.start();
        }

        System.out.println("Setting up");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        driver = new AppiumDriver<>(server.getUrl(), caps);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
         if (driver != null) {
            driver.quit();
            server.stop();
            process.children().forEach(ProcessHandle::destroy);
            process.destroyForcibly().waitFor();

        }
    }

    @Test
    void testAppium() {
        new MainPage(driver)
                .visit()
                .goToAlarm()
                .expandAlarm(1)
                .clickOnDay("Monday")
                .clickOnDay("Tuesday")
                .clickOnDay("Wednesday");
    }

    @Test
    void testCreateAlarm() {
        new MainPage(driver)
                .visit()
                .goToAlarm()
                .createNewAlarm(12,30, new String[]{"Monday", "Friday"});
    }

    @Test
    void testDeleteAlarm() {
        new MainPage(driver)
                .visit()
                .goToAlarm()
                .deleteAlarm(2);
    }


}
