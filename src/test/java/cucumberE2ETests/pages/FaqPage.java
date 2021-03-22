package cucumberE2ETests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.junit.jupiter.api.Assertions.*;

public class FaqPage extends MainPage {

    @FindBy(id = "wiki-search")
    protected WebElement helpSearch;
    @FindBy(name = "B1")
    protected WebElement helpSearchButton;
    @FindBy(xpath = "//table[@class='forum_table']")
    protected WebElement forumTable;
    @FindBy(xpath = "//a[@href='/wiki/page/BoardGameGeek_FAQ']")
    protected WebElement faqArticle;

    public FaqPage(WebDriver driver) {
        super(driver);
        root = "https://www.boardgamegeek.com/wiki/page/BoardGameGeek_FAQ";
    }

    public FaqPage visit() {
        driver.get(root);
        return this;
    }

    public FaqPage search(String input) {
        helpSearch.sendKeys(input + Keys.ENTER);
        return this;
    }

    public WebElement checkResultTable(String searchResult) {
        return forumTable.findElement(By.xpath("//a[@href='/wiki/page/" + searchResult + "']"));
    }

    public boolean searchDisplayed() {
        return helpSearch.isDisplayed();
    }

    public boolean articleDisplayed() {
        return faqArticle != null;
    }

    public FaqPage isSearchDisplayed() {
        assertTrue(helpSearch.isDisplayed());
        return this;
    }

    public FaqPage isArticleDisplayed() {
        assertNotNull(faqArticle);
        return this;
    }

    public FaqPage isResultFound(String searchResult) {
        assertNotNull(checkResultTable(searchResult));
        return this;
    }
}
