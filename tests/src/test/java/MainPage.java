import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

import org.openqa.selenium.support.ui.*;

import org.junit.*;

public class MainPage extends PageBase {
	private By footerLocator = By.xpath("//div[contains(@class,'footer-block')]");
	private By searchBarOpenButtonLocator = By.className("search-bar-toggler");
	private By searchBarLocator = By.name("search");
	
    public MainPage(WebDriver driver)
    {
        super(driver);
        this.driver.get("https://www.inf.elte.hu/en/");
    }

    public String getFooterText()
    {
        WebElement footer = this.driver.findElement(footerLocator);
		return footer.getText();
    }

    public void openTheSearchBar()
    {
        WebElement searchBarOpenButton = waitAndReturnElement(searchBarOpenButtonLocator);
		searchBarOpenButton.click();
    }

    public SearchResultPage search(String keys)
    {
       	WebElement searchBar = waitAndReturnElement(searchBarLocator);
		searchBar.sendKeys(keys);

        return new SearchResultPage(this.driver);
    }
}