package cucumberE2ETests.pages;

import cucumberE2ETests.utility.ManagedDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected String root;
    protected ManagedDriver managedDriver;

    public BasePage(ManagedDriver managedDriver) {
        this.managedDriver = managedDriver;
        this.driver = managedDriver.getDriver();
        PageFactory.initElements(driver, this);
    }

    public BasePage visit() {
        driver.get(root);
        return this;
    }

    public WebElement find(By element) {
        return driver.findElement(element);
    }

}
