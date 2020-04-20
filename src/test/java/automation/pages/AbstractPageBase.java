package automation.pages;

import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPageBase {
    protected WebDriver driver= Driver.getDriver();
    protected WebDriverWait wait= new WebDriverWait(driver,15);
    @FindBy(id = "user-name")
    WebElement userName;

    public AbstractPageBase(){
        PageFactory.initElements(driver,this);
    }


    public String getCurrentUserName(){
        return wait.until(ExpectedConditions.visibilityOf(userName)).getText().trim();
    }

    public void navigateOnTopMenu(String tabName){
        String tabXpath="//div[@id='feed-add-post-form-tab']//span[text()='"+tabName+"']";
        WebElement tabElement=driver.findElement(By.xpath(tabXpath));
        wait.until(ExpectedConditions.elementToBeClickable(tabElement)).click();
    }


}