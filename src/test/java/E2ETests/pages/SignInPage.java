package E2ETests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//form[@name='loginform']")
    public WebElement loginForm;
    @FindBy(id = "inputUsername")
    public WebElement username;
    @FindBy(id = "inputPassword")
    public WebElement password;

    public void signIn(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }
}
