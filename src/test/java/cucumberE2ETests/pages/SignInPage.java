package cucumberE2ETests.pages;

import utilityClasses.utility.Entry;
import utilityClasses.utility.ManagedDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//form[@name='loginform']")
    protected WebElement loginForm;
    @FindBy(id = "inputUsername")
    protected WebElement username;
    @FindBy(id = "inputPassword")
    protected WebElement password;

    public SignInPage(ManagedDriver managedDriver) {
        super(managedDriver);
    }


    public void signIn(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
    }

    @When("I enter username and password")
    public void signIn(DataTable table) {
        Entry entry = new Entry(table, true);
        signIn(entry.getRow(0).get(0), entry.getRow(0).get(1));
    }

    @Then("popup is shown")
    public SignInPage isFormDisplayed() {
        assertTrue(loginForm.isDisplayed());
        return this;
    }

    @Then("it contains field username")
    public SignInPage isUsernameDisplayed() {
        assertTrue(username.isDisplayed());
        return this;
    }

    @Then("it contains field password")
    public SignInPage isPasswordDisplayed() {
        assertTrue(password.isDisplayed());
        return this;
    }
}
