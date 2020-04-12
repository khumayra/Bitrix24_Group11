package automation.pages.login;

import automation.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy (name = "USER_LOGIN")
    public WebElement userName;

    @FindBy  (name="USER_PASSWORD")
    public WebElement password;

    @FindBy (className = "login-btn")
    public WebElement loginBtn;

}
