package com.paylocity.stepdef;

import com.paylocity.utilities.ReadConfigValues;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

public class Hooks {

    public static WebDriver driver;
    @Before
    public void browserSetUp() {
        if (ReadConfigValues.getProperty("browser").equalsIgnoreCase(BrowserType.CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(ReadConfigValues.getProperty("hostUrl"));

        } else if (ReadConfigValues.getProperty("browser").equalsIgnoreCase(BrowserType.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(ReadConfigValues.getProperty("hostUrl"));
        } else if (ReadConfigValues.getProperty("browser").equalsIgnoreCase(BrowserType.EDGE)) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.get(ReadConfigValues.getProperty("hostUrl"));
        } else if (ReadConfigValues.getProperty("browser").equalsIgnoreCase(BrowserType.SAFARI)) {
            driver = new SafariDriver();
            driver.manage().window().maximize();
            driver.get(ReadConfigValues.getProperty("hostUrl"));
        } else {
            Assert.fail("Please select appropriate browser value from {chrome,firefox,MicrosoftEdge and safari}. The current browser value is: " + ReadConfigValues.getProperty("browser"));

        }
    }




    @After
    public void closeBrowser()
    {
        if (driver!=null)
           driver.quit();
    }
}
