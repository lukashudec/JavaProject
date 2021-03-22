package cucumberE2ETests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GeekSearchResultPage extends MainPage {

    public GeekSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getGameLink(String input) {
        return driver.findElements(By.linkText(input));
    }

    public List<WebElement> getGameImage(String input) {
        return driver.findElements(By.xpath("//img[@alt='Board Game: " + input + "']"));
    }

    public GeekSearchResultPage isGameLinkFound(String input) {
        assertNotEquals(0, getGameLink(input).size());
        return this;
    }

    public GeekSearchResultPage isGameImageFound(String input) {
        assertNotEquals(0, getGameImage(input).size());
        return this;
    }
}
