package locators;

import org.openqa.selenium.By;

public class AccountPageLocators {
    public By likedProducts = By.cssSelector("a[title='Beğendiklerim']");
    public By selectProduct = By.cssSelector("div[id*='-AddToCart']");
    public By popupText = By.cssSelector("div[class='hb-toast-text']");
    public By goProduct = By.cssSelector("div[class='product-item ']");
    public By cart = By.cssSelector("a[class='sf-HeaderButton-37P9E']");
    public By closePopup = By.cssSelector("svg[fill='#9B9B9B']");
    public By moveToContainer = By.cssSelector("div[class*='product_img']");
    public By btnDelete = By.cssSelector("a[class='delete_product_3DFC0']");
    public By isEmpty = By.cssSelector("div[class='content_Z9h8v']>h1");
    public By productTextAfterCart = By.cssSelector("div[id*='-Title']");
}
