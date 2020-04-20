package automation.pages.login;

import automation.pages.AbstractPageBase;
import automation.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPageBase {

    @FindBy(name = "USER_LOGIN")
    WebElement userName;
    @FindBy(name = "USER_PASSWORD")
    WebElement password;
    @FindBy(className = "login-btn")
    WebElement loginButton;
    @FindBy(className = "errortext")
    WebElement errorMessage;

    public void defaultLogin(){
        userName.sendKeys(ConfigurationReader.getProperty("hr"));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        loginButton.click();
    }
    public void login(String user, String pass){
        userName.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
    }
    public String getErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText().trim();
    }
    public void loginAsHelpDesk(){
        userName.sendKeys(ConfigurationReader.getProperty("helpdesk"));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        loginButton.click();
    }
}