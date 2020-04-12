package automation.pages.login;

import automation.utilities.BrowserUtils;
import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(name="USER_LOGIN")
    public WebElement userName;

    @FindBy(name="USER_PASSWORD")
    public WebElement password;

    @FindBy(className ="login-btn")
    public WebElement loginButton;


    public LoginPage (){
        PageFactory.initElements(Driver.getDriver(),this);

    }
    public void login() {
        userName.sendKeys(ConfigurationReader.getProperty("helpdesk"));
        password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(3);
    }


}
