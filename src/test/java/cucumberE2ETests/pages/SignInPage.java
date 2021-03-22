package cucumberE2ETests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.*;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//form[@name='loginform']")
    protected WebElement loginForm;
    @FindBy(id = "inputUsername")
    protected WebElement username;
    @FindBy(id = "inputPassword")
    protected WebElement password;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void signIn(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }

    public SignInPage isFormDisplayed() {
        assertTrue(loginForm.isDisplayed());
        return this;
    }

    public SignInPage isUsernameDisplayed() {
        assertTrue(username.isDisplayed());
        return this;
    }

    public SignInPage isPasswordDisplayed() {
        assertTrue(password.isDisplayed());
        return this;
    }
}
