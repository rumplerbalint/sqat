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

public class SearchResultPage extends PageBase {
    public SearchResultPage(WebDriver driver)
    {
        super(driver);
        waitAndReturnElement(bodyLocator);
    }

    public void clickByText(String resultName)
    {
        By locator = By.xpath("//h2[contains(text(),'"+ resultName +"')]//ancestor::a[contains(@class, 'text-wrapper')]");
        waitAndReturnElement(locator).click();
    }
}