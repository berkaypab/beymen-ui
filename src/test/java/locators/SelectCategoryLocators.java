
package locators;

import org.openqa.selenium.By;

public class SelectCategoryLocators {
    public By mainCategory = By.cssSelector("li[class='category-level-0']:nth-of-type(1)");
    public By subCategory = By.cssSelector("li[class='category-level-1']:nth-of-type(3)");
    public By productQuantity = By.cssSelector("span[class='totalItems tolkien']>strong");
    public By secondPage = By.cssSelector("li>a[class='page-2 ']");
}