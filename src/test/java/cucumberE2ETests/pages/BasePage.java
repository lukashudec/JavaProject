package cucumberE2ETests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver driver;
    protected String root;

    public BasePage go() {
        driver.get(root);
        return this;
    }

    public WebElement find(By element) {
        return driver.findElement(element);
    }

}
