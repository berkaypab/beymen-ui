import com.thoughtworks.gauge.Step;
import methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.io.IOException;

public class StepImplementation {
    private Methods methods;

    public StepImplementation() {
        methods = new Methods();
    }

    private By womanMenu = By.cssSelector("li[class='kadin']");
    private By clothing = By.linkText("GİYİM");
    private By discount = By.cssSelector("span[class='m-productCard__discnt']");
    private By discountedProduct = By.xpath("//span[@class='m-productCard__discnt']/ancestor::div[2]/a[2]/span");
    private By newPrice = By.xpath(" //span[@class='m-productCard__discnt']/following-sibling::span/span[2]");
    private By oldPrice = By.xpath(" //span[@class='m-productCard__discnt']/following-sibling::span/span[1]");


    @Step("Title :<key> control")
    public void getTitleAndCheck(String text) {

        Assert.assertTrue("Title match not found", methods.getTitle().toLowerCase().contains(text));

    }

    @Step("Hover on woman menu")
    public void hoverWoman() {

        methods.actionHover(womanMenu);

    }

    @Step("Click clothing menu")
    public void clickClothingBtn() {

        methods.click(clothing);

    }


    @Step("Check url contains <key>")
    public void getUrl(String text) {
        Assert.assertTrue("Url not contains given parameter ", methods.getCurrentUrl().toLowerCase().contains(text));

    }


    @Step("First discounted product")
    public void findFirstDiscountedProduct() {
        System.out.println("First discounted product name : " + methods.getText(discountedProduct));
    }

    @Step("Print prices")
    public void printPricesAndFormat() {

        System.out.println("Old price: " + methods.getText(oldPrice));
        System.out.println("New price: " + methods.getText(newPrice));
        methods.editString(oldPrice);
        methods.editString(newPrice);
    }

    @Step("Write difference value to txt file")
    public void writeDiff() throws IOException {
        String str = String.valueOf(methods.editString(oldPrice) - methods.editString(newPrice));
        methods.txtFileWrite(str);
    }

}
