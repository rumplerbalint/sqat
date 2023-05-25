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

public class PageBase {
	
	protected WebDriver driver;
	private WebDriverWait wait;
	
    protected By bodyLocator = By.tagName("body");

    public PageBase(WebDriver driver)
    {
        this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
    }

	protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getBodyText()
    {
        WebElement bodyElement = waitAndReturnElement(bodyLocator);
        return bodyElement.getText();
    }
}