package automation.pages.file;

import automation.pages.AbstractPageBase;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilePage extends AbstractPageBase {

    @FindBy(xpath = "//span[contains(@id,'feed-add-post-form-link-text')]")
    public WebElement moreTab;

    @FindBy(xpath = "(//div[starts-with(@id,'feed-post-text-contentview-CALENDAR_EVENT')])[1]//a[contains(@id,'feed-event-view-link')]")
    private WebElement latestEventLink;

    @FindBy(xpath = "(//div[starts-with(@id,'feed-post-text-contentview-CALENDAR_EVENT')])[1]//iframe")
    private WebElement latestEventVideoFrame;

    @FindBy(xpath = "(//span[@class ='menu-popup-item-text'])[1]")
    private WebElement fileTab;

    @FindBy(xpath = "//span[@class ='menu-popup-item-text']")
    private WebElement tabsUnderMore;

    public void getTabsOnMore(int tabNum) {

        BrowserUtils.waitForPageToLoad(15);
        wait.until(ExpectedConditions.elementToBeClickable(moreTab)).click();
BrowserUtils.wait(6);
        WebElement element = Driver.getDriver().findElement(By.xpath("(//span[@class ='menu-popup-item-text'])[" + tabNum + "]"));

        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();


    }

    public void clickOnFile() {


        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("feed-add-post-form-link-text"))).click();

        BrowserUtils.wait(5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='menu-popup-item menu-popup-no-icon feed-add-post-form-file feed-add-post-form-file-more ']/span[@class='menu-popup-item-text']"))).click();
        BrowserUtils.wait(5);


    }

//Method that verifies a file is downloaded.

    public boolean isFileDownloaded(String path, String filename) {

        return Files.exists(Paths.get(path, filename));
    }

    public static File createTempFile(String prefix, String suffix, File directory) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("hello", ".tmp");
        } catch (IOException e) {

        }
        System.out.println("Temp file On Default Location: " + tempFile.getAbsolutePath());
        try {
            tempFile = File.createTempFile("hello", ".tmp", new File("C:/"));
        } catch (IOException e) {

        }
        return tempFile;
    }

}