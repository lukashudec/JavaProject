package appiumTests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.cucumber.java.en.Given;


public class MainPage extends AbstractPage {
    @AndroidFindBy(accessibility = "Clock")
    protected MobileElement  rootLocator;
    @AndroidFindBy(accessibility = "Alarm")
    protected MobileElement  alarm;
    @AndroidFindBy(accessibility = "Clock")
    protected MobileElement  clock;
    @AndroidFindBy(accessibility = "Timer")
    protected MobileElement  timer;
    @AndroidFindBy(accessibility = "StopWatch")
    protected MobileElement stopWatch;

    public MainPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MainPage visit() {
        rootLocator.click();
        return this;
    }

    @Given("I am on the main page")
    public MainPage openMainPage() {
        return visit();
    }

    public AlarmPage goToAlarm() {
        alarm.click();
        return new AlarmPage(driver);
    }

    public MainPage goToClock() {
        clock.click();
        return this;
    }

    public MainPage goToStopWatch() {
        stopWatch.click();
        return this;
    }

    public MainPage goToTimer() {
        timer.click();
        return this;
    }
}
