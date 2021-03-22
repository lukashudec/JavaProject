package cucumberE2ETests.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(name = "searchTerm")
    protected WebElement search_bar;
    @FindBy(linkText = "Sign In")
    protected WebElement signInButton;
    @FindBy(xpath = "//button[contains(.,'Help ')]")
    protected WebElement helpDropdown;
    @FindBy(linkText = "FAQ")
    protected WebElement faqButton;

    public MainPage(WebDriver driver) {
        super(driver);
        this.root = "https://www.boardgamegeek.com/";
    }

    public MainPage visit() {
        driver.get(root);
        return this;
    }

    public MainPage clickOnHelp() {
        helpDropdown.click();
        return this;
    }

    public FaqPage clickOnFaq() {
        faqButton.click();
        return new FaqPage(driver);
    }

    public SignInPage clickOnSignIn() {
        signInButton.click();
        return new SignInPage(driver);
    }

    public GeekSearchResultPage searchGames(String input) {
        search_bar.sendKeys(input + Keys.ENTER);
        return new GeekSearchResultPage(driver);
    }
}
