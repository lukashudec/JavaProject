package cucumberE2ETests.pages;

import utilityClasses.utility.ManagedDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected String root;
    protected ManagedDriver managedDriver;

    public BasePage(ManagedDriver managedDriver) {
        this.managedDriver = managedDriver;
        PageFactory.initElements(managedDriver.getDriver(), this);
    }

    public BasePage visit() {
        managedDriver.getDriver().get(root);
        return this;
    }

    public WebElement find(By element) {
        return managedDriver.getDriver().findElement(element);
    }

}
