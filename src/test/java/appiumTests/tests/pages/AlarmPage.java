package appiumTests.tests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AlarmPage extends MainPage {
    @AndroidFindBy(accessibility = "Alarm")
    protected MobileElement rootLocator;

    public AlarmPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }
}
