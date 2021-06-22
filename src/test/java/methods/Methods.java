package methods;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import req.RequiredMethods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class Methods {

    private RequiredMethods requiredMethods;
    private WebDriver webDriver;
    public WebDriverWait wait;
    JavascriptExecutor js;

    public Methods() {
        requiredMethods = new RequiredMethods();
        this.webDriver = BaseTest.driver;
        js = (JavascriptExecutor) BaseTest.driver;
        wait = new WebDriverWait(BaseTest.driver, 15);
        requiredMethods = new RequiredMethods();
    }


    public WebElement findElement(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return webDriver.findElement(by);
    }

    public void req() throws IOException {
        requiredMethods.checkBroken();
    }

    public void moveDown(WebElement element, int y) {

        //js.executeScript("arguments[0].scrollIntoView();", element);

        js.executeScript("window.scrollBy(0," + y + ")");
    }

    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    public WebElement findElementOnPage(By by, int i) {
        List<WebElement> list = findElements(by);
        return list.get(i);
    }

    public void click(By by) {
        findElement(by).click();
    }

    public void actionHover(By by) {
        Actions action = new Actions(webDriver);
        action.moveToElement(findElement(by)).perform();
    }

    public void sendKeys(By by, String text) {
        findElement(by).sendKeys(text);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getText(By by) {
        return findElement(by).getText();
    }

    public void clickMoreContentButton() {
        webDriver.findElement(By.id("moreContentButton")).click();
    }

    public int editString(By by) {
        String str = webDriver.findElement(by).getText().replaceAll("[^a-zA-Z0-9]", "");
        String[] values = str.split("0");
        return Integer.parseInt(values[0]);

    }

    public void txtFileWrite(String text) throws IOException {
        File fileData = new File("docs/text.txt");
        FileWriter writeTxt = new FileWriter(fileData);
        writeTxt.write(text);
        writeTxt.close();
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public Boolean isEnabled(By by) {
        return findElement(by).isEnabled();
    }

    public Boolean isDisplayed(By by) {
        return findElement(by).isDisplayed();
    }

    public void jsClick(By by) {
        WebElement element = findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void select(By by, String text) {
        Select select = new Select(webDriver.findElement(by));
        select.selectByValue(text);
    }


}