package E2ETests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FaqPage extends BoardGameBasePage {

    public FaqPage(WebDriver driver) {
        super(driver);
        root = "https://www.boardgamegeek.com/wiki/page/BoardGameGeek_FAQ";
    }

    @FindBy(id = "wiki-search")
    public WebElement helpSearch;
    @FindBy(name = "B1")
    public WebElement helpSearchButton;
    @FindBy(xpath = "//table[@class='forum_table']")
    public WebElement forumTable;
    @FindBy(xpath = "//a[@href='/wiki/page/BoardGameGeek_FAQ']")
    public WebElement faqArticle;

    public WebElement checkResultTable(String searchResult) {
        return forumTable.findElement(By.xpath("//a[@href='/wiki/page/" + searchResult + "']"));
    }
}
