package cucumberE2ETests.pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends BoardGameBasePage {
    public MainPage(WebDriver driver) {
        super(driver);
        this.root = "https://www.boardgamegeek.com/";
    }
}
