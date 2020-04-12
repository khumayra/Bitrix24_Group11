package automation.pages;

import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


/**
 * This class will be extended by page classes
 * Ant common webelements/locators can be stored here
 * Since navigation menu doesn't belong to particular page
 * We cannot really create a dedicated page class to store
 * elements from that menu
 */
public abstract class AbstractPageBase {
    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 20);

    public void navigateTo(String menuName) {

        String name = "//span [@class = 'menu-item-link-text' and contains (text (), '" + menuName + "')]";
        WebElement menuElement = driver.findElement(By.xpath(name));

      /* One shot way:
         WebElement menuElement = Driver.getDriver().findElement(By.xpath("//span [@class = 'menu-item-link-text' and contains (text (), '"+menuName+"')]"));
      */

        wait.until(ExpectedConditions.visibilityOf(menuElement));
        wait.until(ExpectedConditions.elementToBeClickable(menuElement));
        menuElement.click();

    }
    public String pageSubtitle (){
    WebElement subtitle = Driver.getDriver().findElement(By.id("pagetitle"));
    wait.until(ExpectedConditions.visibilityOf(subtitle));

    String subTitleText = subtitle.getText();
    return subTitleText;


    }



}

