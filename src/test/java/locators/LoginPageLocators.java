package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public By myAccount = By.id("myAccount");
    public By login = By.cssSelector("a[title='Giriş yap']");
    public By username = By.cssSelector("input[name='username']");
    public By password = By.cssSelector("input[name='password']");
    public By btnLogin = By.cssSelector("button[type='submit']");
}