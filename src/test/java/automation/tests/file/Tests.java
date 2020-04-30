package automation.tests.file;

import automation.pages.file.FilePage;
import automation.pages.login.LoginPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Tests extends AbstractTestBase {

    LoginPage loginPage = new LoginPage();
    FilePage filePage = new FilePage();


    /*
    1. User should be able to click on upload files icon to upload files and pictures from
    local disks, download from external drive, select documents
    from bixtrix24, and create files to upload.
     */

    @Test
    public void userStory5() {

//temp file creation for the test
        File temp = new File("Test");
        String path = "";
        String fileName = "";
        test = report.createTest("verify  to click on upload files icon to upload files");
        try {
            String pathCommon = System.getProperty("user.dir");
            temp = File.createTempFile("UserStory_5_Test", ".txt", new File(pathCommon));
            System.out.println("Temp file created : " + temp.getAbsolutePath());
            System.out.println("Temp file exists : " + temp.exists());
            path = temp.getAbsolutePath();
            fileName = temp.getName();

        } catch (IOException e) {

        }
        loginPage.defaultLogin();
        test.info("Login as HR");
       // loginPage.("Activity Stream");

        filePage.clickOnFile();

        BrowserUtils.wait(3);
        // ==========
        Driver.getDriver().findElement(By.xpath("//input[contains(@name,'bxu_files[]')]")).sendKeys(path);
        BrowserUtils.wait(3);
        String fileNameUploaded = Driver.getDriver().findElement(By.xpath("//td[@class='files-name']/span[1]/span[1]")).getText();


        //verification part:
        Assert.assertTrue(fileNameUploaded.contains(fileName), "File name is not on upload list");

        //temp.delete(); //For deleting immediately
        temp.deleteOnExit(); //Delete on runtime exit
        test.pass("File uploaded successfully.");

    }



    /*
    Optional Test: User should be able to click "Applications" and see all
    available applications.

     */

    @Test
    public void test2() {

        test = report.createTest("verify the applications are displayed");
        loginPage.defaultLogin();
        test.info("Login as HR");
        loginPage.navigateOnTopMenu("Activity Stream");
        Driver.getDriver().findElement(By.xpath("//span[@class='menu-favorites-more-text'][contains(.,'More...')]")).click();
        BrowserUtils.wait(3);


        Driver.getDriver().findElement(By.xpath("//span[@class='menu-item-link-text'][contains(.,'Applications')]")).click();

        List<WebElement> applications = Driver.getDriver().findElements(By.className("mp-item-title"));

        for (WebElement eachApp : applications) {

            Assert.assertTrue(eachApp.isDisplayed());
        }
        test.pass("Applications visibility is verified");
    }

}
