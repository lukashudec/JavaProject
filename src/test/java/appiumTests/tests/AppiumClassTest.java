package appiumTests.tests;

import appiumTests.tests.pages.MainPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch;

class AppiumClassTest {

    static AppiumDriver<MobileElement> driver;
    static AppiumDriverLocalService server;
    static Process process;

    public static void startEmulator() throws IOException {
        process = new ProcessBuilder("C:/Users/lenovo/AppData/Local/Android/Sdk/emulator/emulator.exe", "-avd", "Gala_11", "-no-snapshot-save")
                .start();
    }

    @BeforeAll
    public static void startEmulatorAndAppium() throws IOException {
        startEmulator();

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .usingAnyFreePort()
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
            startEmulator();
            server.start();
        }

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability("skipDeviceInitialization", true);
        caps.setCapability("skipServerInstallation", true);
        caps.setCapability("appPackage", "com.google.android.deskclock");
        caps.setCapability("appActivity", "com.android.deskclock.DeskClock");
        driver = new AppiumDriver<>(server.getUrl(), caps);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Stopwatch timer = Stopwatch.createStarted();
         if (driver != null) {
            driver.quit();
            server.stop();
            process.children().forEach(ProcessHandle::destroy);
            process.destroyForcibly().waitFor();
        }
        System.out.println("AfterEach duration : "+timer.stop());
    }
        @Test
        void testAppium() {
        // this test can be remade and addted to CreateDelete -> CreateDeleteEdit to save additional time on test running
            Stopwatch timer = Stopwatch.createStarted();
            new MainPage(driver)
                    .visit()
                    .goToAlarm()
                    .expandAlarm(1)
                    .clickOnDay("Monday")
                    .clickOnDay("Tuesday")
                    .clickOnDay("Wednesday");
            System.out.println("Test body : "+timer.stop());
        }

        @Test
        void testCreateDeleteAlarm() {
            Stopwatch timer = Stopwatch.createStarted();
        /* two tests combined into one
        * starting appium,emulator,driver is time consuming so it should be better to write more complex tests
        * even though writing complex tests is antipattern */
            new MainPage(driver)
                    .visit()
                    .goToAlarm()
                    .createNewAlarm(12, 30, new String[]{"Monday", "Friday"})
                    .goToAlarm()
                    .deleteAlarm(1);
            System.out.println("Test body : "+timer.stop());
        }

}
