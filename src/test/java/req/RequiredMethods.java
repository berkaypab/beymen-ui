package req;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;

public class RequiredMethods {
    private static int statusCode;
    private final WebDriver driver;

    public RequiredMethods() {
        this.driver = BaseTest.driver;

    }

    public static int getResponseCode(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("GET");
        huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36");
        huc.connect();
        return huc.getResponseCode();
    }

    public void checkBroken() throws IOException {

        String homePage = "https://www.hepsiburada.com/";
        String url;
        HttpURLConnection huc;
        int respCode = 200;


        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (int i = 0; i < links.size(); i++) {
            if (!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))) {
                if (links.get(i).getAttribute("href").contains("http")) {


                    statusCode = getResponseCode(links.get(i).getAttribute("href").trim());

                    if (statusCode >= 400) {
                        System.out.println("4xx Client Errors # " + i + " " + links.get(i).getAttribute("href"));
                    }
                    if (statusCode >= 200 && statusCode < 300) {
                        System.out.println("Valid Link # " + i + " " + links.get(i).getAttribute("href"));
                    }
                }
            }
        }
    }

    public static void isExist(WebDriver driver, String productId) {


        List<WebElement> items = driver.findElements(By.xpath("//div[@id='view']/ul/li/div/div/a[@class='plink']"));
        boolean isDeleted = false;

        for (int i = 0; i < items.size(); i++) {

            String str = items.get(i).getAttribute("data-id");
            if (str.equals(productId)) {
                isDeleted = false;
                break;
            } else {
                isDeleted = true;

            }

        }
        if (isDeleted)
            System.out.println("Product deleted");
        else
            System.out.println("Product still in wish list");


    }

}
