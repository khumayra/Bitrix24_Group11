package automation.tests.task;

import automation.pages.login.LoginPage;
import automation.pages.task.TaskPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TaskTests extends AbstractTestBase {
    private By textEditorBarBy = By.xpath("//div[@id='bx-html-editor-tlbr-lifefeed_task_form']//span");

    @Test
    public void verifyHighPriorityChkBx () throws Exception{
        test = report.createTest("Selecting high priority checkbox make task top priority task");
        String titleValue = "Very Important Task";
        String descriptionValue = "Make it High Priority";
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        TaskPage taskPage = new TaskPage();
        taskPage.navigateTo("Task");

        //filling out task form
//        taskPage.enterTaskTitle(titleValue);
//        taskPage.enterTaskDescription(descriptionValue);
//        taskPage.selectHighPriority();
//        taskPage.clickOnSave();
//        taskPage.pressViewTaskBtnPopUp();
//        driver.switchTo().activeElement();
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div[@class='side-panel-content-container']//iframe")));
//        String actual = driver.findElement(By.id("task-detail-important-button")).getAttribute("class");
//        String expected = "task-info-panel-important mutable";
//        Assert.assertEquals(actual,expected);
//
        Thread.sleep(4000);
        WebElement switchLabel = driver.findElement(By.xpath("//label[.='High Priority']"));
        String colorRGB = ((JavascriptExecutor)driver)
                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');",switchLabel).toString();
        System.out.println(colorRGB);
        taskPage.selectHighPriority();
        colorRGB = ((JavascriptExecutor)driver)
                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');",switchLabel).toString();
        System.out.println(colorRGB);
        //String expected = "task-info-panel-important mutable";
        //Assert.assertEquals(actual,expected);

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
