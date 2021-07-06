import com.thoughtworks.gauge.Step;
import locators.*;
import methods.Methods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.io.IOException;

public class StepImplementation {

    public static Logger logger = LogManager.getLogger(StepImplementation.class);
    private static Methods methods;
    WebElement element = null;
    LoginPageLocators loginPage;
    SearchPageLocators searchPage;
    SelectCategoryLocators selectCategory;
    ProductDetailPageLocators pdLocators;
    AccountPageLocators accLocators;
    String productText;
    String tempString = null;
    int tempInteger = 0;


    public StepImplementation() {
        methods = new Methods();
        loginPage = new LoginPageLocators();
        searchPage = new SearchPageLocators();
        selectCategory = new SelectCategoryLocators();
        pdLocators = new ProductDetailPageLocators();
        accLocators = new AccountPageLocators();
    }

    @Step("<key> saniye bekle")
    public void waitBySecond(int x)  {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Thread "+x+" saniye bekletildi");
    }

    @Step({"Sitenin açılıp açılmadığı kontrol edilir"})
    public void getTitleAndCheck() {
        Assert.assertEquals(methods.getTitle(), "Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com");
    }


    @Step("Giriş yap tabının üstüne gelinir ve giriş yap butonuna tıklanır.")
    public void signInHover() throws InterruptedException {
        methods.actionHover(loginPage.myAccount);
        Thread.sleep(2000);
        methods.click(loginPage.login);

    }

    @Step("Kullanıcı adı , şifresi girilir ve giriş yap butonu tıklanır")
    public void sendUserNameAndPassword() {
        methods.sendKeys(loginPage.username, Constants.userName);
        logger.info("kullanici adi girildi");
        logger.error("abc");
        methods.sendKeys(loginPage.password, Constants.userPassword);
        logger.info("kullanici sifresi girildi");
        methods.click(loginPage.btnLogin);
        logger.info("giris butonuna tiklandi");
    }

    @Step("Arama kısmına telefon yazılır ve arama yapılır")
    public void searchByTextOnSearchBox() throws InterruptedException {
        methods.sendKeys(searchPage.inputLabel, Constants.searchByText);
        logger.info("arama label'ina " + Constants.searchByText + " yazildi");
        methods.click(searchPage.searchButton);
        logger.info("arama butonuna tiklandi");
        Thread.sleep(5000);

    }

    @Step("Kategoriler secilir ve urunlerin listelendigi dogrulanir")
    public void selectCategories() {
        methods.click(selectCategory.mainCategory);
        logger.info("ana kategori secildi");
        methods.click(selectCategory.subCategory);
        logger.info("alt kategori secildi");
        tempString = methods.getText(selectCategory.productQuantity);
        String str = tempString;
        String resultStr="";
        resultStr = resultStr + str.charAt(1)+str.charAt(2)+str.charAt(4)+str.charAt(5)+str.charAt(6);

        tempInteger = Integer.parseInt(resultStr);
        Assert.assertTrue("Ürün Bulunamadı", tempInteger > 0);
        logger.info("urun bulunamadi kontolu yapildi");
        tempInteger = 0;
        tempString = null;
    }

    @Step("ikinci sayfa acılır ve gosterimde oldugu dogrulanir")
    public void pagination() {
        element = methods.findElement(selectCategory.secondPage);
        int px=0;

        element.click();
        element = null;
        logger.info("Bir sonraki sayfaya gecildi");
    }



    /***
     * Selects nth product given Canstants.nthProduct
     */
    @Step("5. ürün seçilir ve detay sayfasına gidilir")
    public void getNthProduct() {
        element = methods.findElementOnPage(searchPage.products, (Constants.nthProduct - 1));
        int px=0;
        productText = element.findElement(searchPage.getProductHeaderText).getText();
        while (!element.isDisplayed()){
            methods.moveDown(element,px);
            px=px+10;
        }
        element.click();
        logger.info(Constants.nthProduct + ". eleman secildi");
        element = null;
    }

    /***
     * Click Like Button in Product Detail Page
     */
    @Step("Ürün detayında beğen butonu tıklanır")
    public void like() {
        methods.click(pdLocators.like);

        logger.info("urun begenildi");
    }

    /***
     * Add product to liked list , makes popup control
     */
    @Step("Ürün listensine eklenir ve popup kontrolu yapılır")
    public void popUpControl() {
        tempString = methods.getText(pdLocators.popUp);
        Assert.assertEquals(tempString, "Ürün listenize eklendi.");
        tempString = null;
    }

    /***
     *Click Liked in User Panel
     */
    @Step("Kullanıcı panelindeki beğendiklerim tıklanır")
    public void goToLiked() throws InterruptedException {
        methods.actionHover(loginPage.myAccount);
        Thread.sleep(2000);
        methods.click(accLocators.likedProducts);
        logger.info("Begendiklerim alanina gidildi");
    }

    /***
     * Compares prices (Before)(After) Cart
     */
    @Step("Fiyat karşılaştırması yapılır")
    public void priceCompare() {
        Assert.assertEquals(methods.getText(accLocators.productTextAfterCart), productText);
    }

    /***
     * Add To Cart Liked Product
     */
    @Step("Beğenilen ürün sepete eklenir")
    public void addToCart() {
        methods.actionHover(accLocators.goProduct);
        methods.click(accLocators.selectProduct);
    }

    /***
     * Add To Cart Popup Control
     */
    @Step("Ürün sepete eklendi popup kontrolü yapılır")
    public void isProductAddedToCartPopupControl() {
        Assert.assertEquals(methods.getText(accLocators.popupText), "Ürün sepete eklendi");
        logger.info("Urun sepete eklendi");
    }

    /***
     * Go To Cart Page
     */
    @Step("Sepetim sayfasına gidilir")
    public void goToCart() throws InterruptedException {
        Thread.sleep(2000);
        methods.click(accLocators.closePopup);
        methods.click(accLocators.cart);
        logger.info("Sepete gidildi");
    }

    /***
     * The Item Added To The Cart Is Removed
     */
    @Step("Sepete eklenen ürün kaldırılır")
    public void deleteProduct() {
        methods.actionHover(accLocators.moveToContainer);
        methods.click(accLocators.btnDelete);
        logger.info("ürün sepetten silindi");
    }

    /***
     * Verify There Is No Product In The Cart
     */
    @Step("Sepette ürün olmadığı doğrulanır")
    public void isEmptyCart() {
        Assert.assertEquals(methods.getText(accLocators.isEmpty), "Sepetin şu an boş");
        logger.info("Sepette ürün olmadığı doğrulandı");
    }
    @Step("Kırık link kontrolü yapılır")
    public void req() throws IOException {
       methods.req();
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private By newPrice = By.xpath(" //span[@class='m-productCard__discnt']/following-sibling::span/span[2]");
    private By oldPrice = By.xpath(" //span[@class='m-productCard__discnt']/following-sibling::span/span[1]");



    @Step("Check url contains <key>")
    public void getUrl(String text) {
        Assert.assertTrue("Url not contains given parameter ", methods.getCurrentUrl().toLowerCase().contains(text));

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
