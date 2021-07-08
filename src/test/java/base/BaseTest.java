package base;


import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseTest {


    public static void setDriver(RemoteWebDriver driver) {
        BaseTest.driver = driver;
    }

    public static RemoteWebDriver driver;

    private final String url = "https://www.hepsiburada.com/";

    @BeforeScenario
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-notifications");



        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        BaseTest.setDriver(new ChromeDriver(options));
        driver.get(url);
    }


    @AfterScenario
    public void after() {
        driver.quit();
    }


}