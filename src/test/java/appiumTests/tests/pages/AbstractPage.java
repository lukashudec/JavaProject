package appiumTests.tests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
    protected AppiumDriver<MobileElement> driver;


    protected MobileElement rootLocator;


    public AbstractPage(AppiumDriver<MobileElement> appiumDriver) {
        this.driver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public AbstractPage visit() {
        rootLocator.click();
        return this;
    }

    public WebElement find(By element) {
        return driver.findElement(element);
    }

}
