package E2ETests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GeekSearchResultPage extends BoardGameBasePage {

    public GeekSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getGameLink(String input) {
        return driver.findElements(By.linkText(input));
    }

    public List<WebElement> getGameImage(String input) {
        return driver.findElements(By.xpath("//img[@alt='Board Game: " + input + "']"));
    }
}
