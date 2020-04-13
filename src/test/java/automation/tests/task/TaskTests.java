package automation.tests.task;

import automation.pages.login.LoginPage;
import automation.pages.task.TaskPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TaskTests extends AbstractTestBase {
    private By textEditorBarBy = By.xpath("//div[@id='bx-html-editor-tlbr-lifefeed_task_form']//span");
    private By streamTimeBy = By.className("feed-time");

    @Test
    public void verifyHighPriorityChkBx (){
        test = report.createTest("Selecting high priority checkbox make task top priority task");
        String titleValue = "Very Important Task";
        String descriptionValue = "Make it High Priority";
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        TaskPage taskPage = new TaskPage();
        taskPage.navigateTo("Task");

        //filling out task form
        taskPage.enterTaskTitle(titleValue);
        taskPage.enterTaskDescription(descriptionValue);
        taskPage.selectHighPriority();
        taskPage.clickOnSave();
        taskPage.pressViewTaskBtnPopUp();
        driver.switchTo().activeElement();
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='side-panel-content-container']//iframe")));
        String actual = driver.findElement(By.cssSelector("#task-detail-important-button")).getAttribute("class");
        String expected = "task-info-panel-important mutable";
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void verifyVisibilityOfTextBar(){
        test = report.createTest("Verify visibility of text editor toolbar");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        TaskPage taskPage = new TaskPage();
        taskPage.navigateTo("Task");
        taskPage.makeEditorTextBarVisible();
        List<WebElement> textEditorBar = driver.findElements(textEditorBarBy);
        Assert.assertTrue(textEditorBar.size()>0);
        test.pass("Visibility of text editor toolbar verified!");
    }

}
