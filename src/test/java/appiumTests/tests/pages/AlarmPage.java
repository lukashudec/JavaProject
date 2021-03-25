package appiumTests.tests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AlarmPage extends MainPage {
    @AndroidFindBy(accessibility = "Alarm")
    protected MobileElement rootLocator;


    public AlarmPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public AlarmPage visit() {
        rootLocator.click();
        return this;
    }

    public AlarmPage expandAlarm(int alarmRow) {
        driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\" Alarm\"])["+alarmRow+"]")
                .click();
        return this;
    }

    public AlarmPage clickOnDay(String day) {
        driver.findElementByAccessibilityId(day).click();
        return this;
    }

    public AlarmPage clickOnHour(int hour) {
        driver.findElementByAccessibilityId(String.valueOf(hour)).click();
        return this;
    }

    public AlarmPage createNewAlarm(int hour, int minute,String[] days) {
        driver.findElementByAccessibilityId("Add alarm").click();
        // hour , minute
        driver.findElementByAccessibilityId("12").click();
        driver.findElementByAccessibilityId("30").click();
        // ok hour
        driver.findElementById("android:id/button1").click();
        // repeat checkbox
        driver.findElementById("com.google.android.deskclock:id/repeat_onoff").click();

        this.clickOnDay("Monday");
        this.clickOnDay("Friday");
        return this;
    }

    public AlarmPage deleteAlarm(int row) {
        this.expandAlarm(row);
        driver.findElementById("com.google.android.deskclock:id/delete").click();
        return this;
    }

    public boolean isExtended() {
        return driver.findElementsByAccessibilityId("Collapse alarm").size() != 0;
    }




}
