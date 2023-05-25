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

public class FirstSeleniumTest {
	
	private WebDriver driver;
	
    @Before
    public void setup() throws MalformedURLException
    {
		ChromeOptions options = new ChromeOptions();
		this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
		this.driver.manage().window().maximize();
    }

	//We want to explain our test here (in this class) in a higher level
	@Test
    public void testSelenium() {
		MainPage mainPage = new MainPage(this.driver);
		System.out.println(mainPage.getBodyText());

		Assert.assertTrue(mainPage.getFooterText().contains("ELTE Faculty of Informatics"));

		mainPage.openTheSearchBar();
		SearchResultPage searchResultPage = mainPage.search("Students\n");

		Assert.assertTrue(searchResultPage.getBodyText().contains("FOUND"));
		Assert.assertTrue(searchResultPage.getBodyText().contains("Current Students")); 

		searchResultPage.clickByText("Current Students");
		/*
		WebElement footer = this.driver.findElement(footerLocator);
		Assert.assertTrue(footer.getText().contains("ELTE Faculty of Informatics"));
		
		WebElement searchBarOpenButton = this.driver.findElement(searchBarOpenButtonLocator);
		searchBarOpenButton.click();
		
		WebElement searchBar = waitAndReturnElement(searchBarLocator);
		searchBar.sendKeys("Students\n");
		
		WebElement bodyElementAgain = waitAndReturnElement(bodyLocator);
		Assert.assertTrue(bodyElementAgain.getText().contains("FOUND"));
		Assert.assertTrue(bodyElementAgain.getText().contains("Current Students")); 
		*/
	}

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}