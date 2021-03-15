package cucumberE2ETests.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardGameBasePage extends BasePage {

    public BoardGameBasePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(name = "searchTerm")
    WebElement search_bar;
    @FindBy(linkText = "Sign In")
    WebElement signInButton;
    @FindBy(xpath = "//button[contains(.,'Help ')]")
    WebElement helpDropdown;
    @FindBy(linkText = "FAQ")
    WebElement faqButton;

    public BoardGameBasePage clickOnHelp() {
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

    public GeekSearchResultPage search(String input) {
        search_bar.sendKeys(input + Keys.ENTER);
        return new GeekSearchResultPage(driver);
    }
}
