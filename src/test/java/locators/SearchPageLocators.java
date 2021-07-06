package locators;

import org.openqa.selenium.By;

public class SearchPageLocators {

    public By inputLabel = By.cssSelector("div[class*='desktopOld']>input[type='text']");
    public By searchButton = By.cssSelector("div[class*='buttonContainer']");
    public By getProductHeaderText = By.cssSelector("p[class='hb-pl-cn']>span");
    public By products = By.cssSelector("li[class='search-item col lg-1 md-1 sm-1  custom-hover not-fashion-flex']");


}