package Practise.PageObjectModel;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGBaseTest {
    public WebDriver driver; // Change to public
    public LoginPage lgnPage;

    @BeforeMethod
    public void setUpBrowser() throws MalformedURLException {
        String huburl = "http://192.168.0.108:4444";
        
      /*  DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.WIN10);
        cap.setBrowserName("chrome");*/
        
ChromeOptions options = new ChromeOptions();
        
//Use setCapability instead of setPlatformName to avoid the error
options.setCapability("platformName", "Windows 11");
options.setCapability("browserName", "chrome");
        
        driver = new RemoteWebDriver(new URL(huburl), options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        // Initialize the PageFactory here
        lgnPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


