package base;


import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    public static WebDriver driver;

    private final String url = "https://beymen.com/";

    @BeforeScenario
    public void setUp() {
        System.setProperty("/Users/testinium/Desktop/rapsodo-automation-ui-project/", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }


    @AfterScenario
    public void after() {
     driver.quit();
    }


}