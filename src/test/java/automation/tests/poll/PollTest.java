package automation.tests.poll;

import automation.pages.login.LoginPage;

import automation.pages.poll.PollPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class PollTest extends AbstractTestBase {

    private By textBarEditorBy = By.id("bx-html-editor-tlbr-idPostFormLHE_blogPostForm");

    private By uploadingFile = By.name("bxu_files[]");

    private By saveIconBy = By.id("undefined");

    private By saveTheVideoLinkIconBy = By.cssSelector("[name='undefined']");

    private By sendBtn = By.id("blog-submit-button-save");

    /**
     * User should be able to click on Visual Editor
     * and see the editor text-bar displays on top of the message box.
     */
    @Test
    public void verifyVisibilityOfTextBar() {
        test = report.createTest("Verify visibility of text editor toolbar");
        LoginPage loginPage = new LoginPage();
        loginPage.defaultLogin();
        PollPage pollPage = new PollPage();
        pollPage.navigateOnTopMenu("Poll");
        pollPage.makeEditorTextBarVisible();

        List<WebElement> textEditorBar = Driver.getDriver().findElements(textBarEditorBy);
        Assert.assertTrue(textEditorBar.size() > 0);
        test.pass("Visibility of text editor toolbar verified!");

    }

    /**
     * User should be able to click on upload files icon to upload files
     * and pictures from local disks, download from external drive,
     * select documents from bixtrix24, and create files to upload.
     */

    @Test
    public void verifyUploadingFile() {
        test = report.createTest("Verify file uploading");
        LoginPage loginPage = new LoginPage();
        loginPage.defaultLogin();
        PollPage pollPage = new PollPage();
        pollPage.navigateOnTopMenu("Poll");
        BrowserUtils.wait(2);


        pollPage.uploadedFileVisibility(System.getProperty("user.dir") + "/pom.xml");

//Driver.getDriver().findElement(uploadingFile).sendKeys((System.getProperty("user.dir")+"/pom.xml"));
        BrowserUtils.wait(2);
    }

    /**
     * User should be able to attach link by clicking on the link icon.
     */
    @Test
    public void verifyAttachingLink() {
        test = report.createTest("Verify attaching the link");
        LoginPage loginPage = new LoginPage();
        loginPage.defaultLogin();
        PollPage pollPage = new PollPage();
        pollPage.navigateOnTopMenu("Poll");
        BrowserUtils.wait(2);

        pollPage.attachingLink("https://www.amazon.com/");
        BrowserUtils.wait(2);
        Driver.getDriver().findElement(saveIconBy).click();
        BrowserUtils.wait(4);
        Driver.getDriver().findElement(sendBtn).click();

    }

    /**
     * User should be able to insert videos
     * by clicking on the video icon and entering the video URL.
     */
    @Test
    public void verifyVideoInsert() {//?
        test = report.createTest("Verify inserting the link video");
        LoginPage loginPage = new LoginPage();
        loginPage.defaultLogin();
        PollPage pollPage = new PollPage();
        pollPage.navigateOnTopMenu("Poll");
        BrowserUtils.wait(2);

        pollPage.insertVideo("https://www.youtube.com/watch?v=BL4-_tVx2rE");
        BrowserUtils.wait(6);
        // wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[@class ='bxhtmled-left-c'] /label")));

        Driver.getDriver().findElement(saveTheVideoLinkIconBy).click();
        BrowserUtils.wait(4);
        pollPage.sendButton();
        List<WebElement> afterLink = Driver.getDriver().findElements(By.xpath("//td[@class ='bxhtmled-left-c'] /label"));
        Assert.assertTrue(afterLink.size() > 0);

        //driver.findElement(saveIconBy).click();

        BrowserUtils.wait(4);
//        Driver.getDriver().findElement(sendBtn).click();
//        BrowserUtils.wait(4);
//        //Assert.assertTrue(Driver.getDriver().findElement(By.cssSelector("div[style*='X9eLWkZBKgQ']")).isDisplayed());


    }

    /**
     * User should be able to create a quote by clicking on the Comma icon.
     */

    @Test
    public void creatingTheQuote() {
        test = report.createTest("Verify creating the quote");
        LoginPage loginPage = new LoginPage();
        loginPage.defaultLogin();
        PollPage pollPage = new PollPage();
        pollPage.navigateOnTopMenu("Poll");
        BrowserUtils.wait(2);

        pollPage.frameCreateQuote("Today is a great day" + LocalDate.now());
        String actual = Driver.getDriver().findElement(By.xpath("//blockquote")).getAttribute("class");
        String expected = "bxhtmled-quote";
        Assert.assertEquals(actual, expected);

        BrowserUtils.wait(4);
        pollPage.sendButton();
        BrowserUtils.wait(4);

        String actual1 = Driver.getDriver().findElement(By.xpath("(//table[@class='blogquote'])[1]")).getText();
        String expected1 = "Today is a beautiful day " + LocalDate.now();

        Assert.assertEquals(actual1, expected1);
    }

    /**
     * User should be able to add mention by clicking on the Add mention icon
     * and select contacts from the lists provided in dropdown.
     */

    @Test
    public void addMentionTest() {
        test = report.createTest("Verify creating the quote");
        LoginPage loginPage = new LoginPage();
        loginPage.defaultLogin();
        PollPage pollPage = new PollPage();
        pollPage.navigateOnTopMenu("Poll");
        BrowserUtils.wait(2);

        pollPage.addMentionAC();
//      WebElement cyberVet = Driver.getDriver().findElement(By.cssSelector("[id*='destDepartmentTab_mention']"));
//        Select selectContacts = new Select(cyberVet);
//        selectContacts.selectByVisibleText("marketing2@cybertekschool.com");

        WebElement textAddress = Driver.getDriver().findElement(By.xpath("//a//div[contains(text(),'marketing2@cybertekschool.com')]"));
        JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
        executor.executeScript("arguments[0].click();", textAddress);

        WebElement employeeAdded = Driver.getDriver().findElement(By.xpath("//span[@class='feed-add-post-destination-text'][contains(text(),'marketing2@cybertekschool.com')]"));
        wait.until(ExpectedConditions.visibilityOf(employeeAdded));
        Assert.assertEquals(employeeAdded.getText().trim(), "marketing2@cybertekschool.com");
        BrowserUtils.wait(3);
        Driver.getDriver().findElement(sendBtn).click();

        BrowserUtils.wait(3);
    }
}
