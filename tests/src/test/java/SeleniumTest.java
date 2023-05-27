import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.*;

public class SeleniumTest {
	
	private WebDriver driver;
	private String email = "oz3f4t@inf.elte.hu";
	private String username = "sqattest";
	private String password = "SQATtest1";
	
    @Before
    public void setup() throws MalformedURLException
    {
		ChromeOptions options = new ChromeOptions();
		this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
		this.driver.manage().window().maximize();
    }

	@Test
    public void testSelenium() {
		MainPage mainPage = new MainPage(this.driver);
		LoginPage loginPage = new LoginPage(this.driver);
		NewTrailersPage newTrailersPage = new NewTrailersPage(this.driver);
		AccountSettingsPage accountSettingsPage = new AccountSettingsPage(this.driver);

		//Reading the page title
		assertTrue(mainPage.readPageTitle().equals("Search IMDb"));

		//Static page test
		mainPage.openNewTrailersPage();
		assertTrue(newTrailersPage.checkTitle().equals("Watch New Movie & TV Trailers"));
		assertTrue(newTrailersPage.countTrailers() >= 6);
		assertTrue(newTrailersPage.getFooterCopyrightText().contains("by IMDb.com, Inc."));
		
		//Login test
		mainPage.openLoginPage();
		loginPage.login(email, password);
		assertTrue(mainPage.getLoggedInUsername().equals(username));

		//Form sending with user
		mainPage.openUserSettings();
		accountSettingsPage.openEditProfile();
		accountSettingsPage.editBio("something interesting");
		accountSettingsPage.openEditProfile();
		assertTrue(accountSettingsPage.checkBio().equals("something interesting"));
		accountSettingsPage.goToHomePage();

		//Logout test
		mainPage.logout();
		System.out.println(mainPage.getLoginInButtonText());
		assertTrue(mainPage.getLoginInButtonText().equals("Sign In"));
	}

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}