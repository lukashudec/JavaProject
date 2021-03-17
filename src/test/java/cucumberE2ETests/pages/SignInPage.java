package cucumberE2ETests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//form[@name='loginform']")
    protected WebElement loginForm;
    @FindBy(id = "inputUsername")
    protected WebElement username;
    @FindBy(id = "inputPassword")
    protected WebElement password;

    public void signIn(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }

    public boolean isDisplayed() {
        return loginForm.isDisplayed();
    }

    public boolean usernameDisplayed() {
        return username.isDisplayed();
    }

    public boolean passwordDisplayed() {
        return password.isDisplayed();
    }
}
