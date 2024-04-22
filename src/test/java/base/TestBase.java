package base;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class TestBase {

    static {
        Configuration.baseUrl = "http://localhost/";
        Configuration.timeout = 5000;
        Configuration.pollingInterval = 100;
        Configuration.headless = true;
//        Configuration.holdBrowserOpen = true;
//        Configuration.browser = "firefox";
//        Configuration.browserSize = "1820x1080";
        Configuration.clickViaJs = true;
        Configuration.reportsFolder = "src/test/resources/reportaze";


    }


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/win/chromedriverr.exe");
//        driver = new ChromeDriver();
//        WebDriverRunner.setWebDriver(driver);

    }

    @After
    public void tearDown () {
//        if (this.driver != null) {
//            this.driver.quit();
//        }
    }

}
