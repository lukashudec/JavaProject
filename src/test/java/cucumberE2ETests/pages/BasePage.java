package cucumberE2ETests.pages;

import org.openqa.selenium.*;
import utilityClasses.utility.ManagedDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.image.BufferedImage;
import java.util.List;

public class BasePage {
    protected String root;
    protected final ManagedDriver managedDriver;
    protected final WebDriver driver;

    public BasePage(ManagedDriver managedDriver) {
        this.managedDriver = managedDriver;
        this.driver = managedDriver.getDriver();
        PageFactory.initElements(managedDriver.getDriver(), this);
    }

    public BasePage visit() {
        driver.get(root);
        return this;
    }

    public WebElement findElement(By element) {
        return driver.findElement(element);
    }

    public List<WebElement> findElements(By element) {
        return driver.findElements(element);
    }

    public BasePage takeScreenshot() {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        return this;
    }

    public void makeBorder(WebElement Element)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(
                "arguments[0].style.border = '3px solid red'",
                Element);
    }

    public void makeBorder(By element) {
        makeBorder(driver.findElement(element));
    }

    /*
    BufferedImage imgA = ImageIO.read(new File("C:/img/picA.jpg"));
    BufferedImage imgB = ImageIO.read(new File("C:/img/picB.jpg"));
    */

    public int bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) { return 0; }
        int pixCount = img1.getWidth()*img1.getHeight();
        int incorrectPixCount = 0;

        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y))
                    incorrectPixCount++;
                }
            }
        return (incorrectPixCount/pixCount)*100;
    }

}
