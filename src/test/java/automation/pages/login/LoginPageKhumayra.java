package automation.pages.login;

import automation.pages.AbstractPageBaseKhumayra;
import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageKhumayra extends AbstractPageBaseKhumayra {
    public LoginPageKhumayra(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy (name = "USER_LOGIN")
    public WebElement userName;

    @FindBy  (name="USER_PASSWORD")
    public WebElement password;

    @FindBy (className = "login-btn")
    public WebElement loginBtn;

    @FindBy (className = "errortext")
    private WebElement warningMsg;

    public void login (){
        userName.sendKeys(ConfigurationReader.getProperty("helpdesk"));
        password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
    }

    /**
     *
     * @param usernameValue email address
     * @param passwordValue password
     */
    public void login (String usernameValue, String passwordValue){
        userName.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);
    }

    /**
     * from configuration.properties file
     * @param cUserName
     */
    public void login (String cUserName){
        try {
            userName.sendKeys(ConfigurationReader.getProperty(cUserName));
            password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
        } catch (Exception e){
            System.out.println("Invalid username in configuration.properties file");
            e.printStackTrace();
        }
    }

    public String getWarningMessageText(){
        return warningMsg.getText();
    }
}
