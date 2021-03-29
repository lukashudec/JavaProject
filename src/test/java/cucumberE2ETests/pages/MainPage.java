package cucumberE2ETests.pages;

import cucumberE2ETests.utility.ManagedDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
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

    public MainPage(ManagedDriver managedDriver) {
        super(managedDriver);
        this.root = "https://www.boardgamegeek.com/";
    }

    @Step
    public MainPage visit() {
        driver.get(root);
        return this;
    }

    @Given("I am on the main page")
    @Step
    public MainPage openMainPage() {
        return visit();
    }

    public MainPage clickOnHelp() {
        helpDropdown.click();
        return this;
    }

    public FaqPage clickOnFaq() {
        faqButton.click();
        return new FaqPage(managedDriver);
    }

    @When("I click on Sign in button")
    public SignInPage clickOnSignIn() {
        signInButton.click();
        return new SignInPage(managedDriver);
    }

    @When("I enter search term: {}")
    @Step
    public GeekSearchResultPage searchGames(String input) {
        search_bar.sendKeys(input + Keys.ENTER);
        return new GeekSearchResultPage(managedDriver);
    }
}
