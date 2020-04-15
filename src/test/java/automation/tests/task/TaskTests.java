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
        // Step 1: Login to WebPage with default creditials
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        // Step 2: Navigate to Task menu
        TaskPage taskPage = new TaskPage();
        taskPage.navigateTo("Task");

        // Step3: Filling out task form
        taskPage.enterTaskTitle(titleValue);
        taskPage.enterTaskDescription(descriptionValue);
        Thread.sleep(4000);
        //--------------------------------------------------------------------------------------
        // Step 4: Checking if High Priority selected in New Task
        WebElement switchLabel = driver.findElement(By.xpath("//label[.='High Priority']"));
//        String actualBackgroundPositionBeforeChecked = ((JavascriptExecutor)driver)
//                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');",switchLabel).toString();
//        Assert.assertEquals(actualBackgroundPositionBeforeChecked,"0px -103px");
        taskPage.selectHighPriority();
        String actualBackgroundPositionAfterChecked = ((JavascriptExecutor)driver)
                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');",switchLabel).toString();
        Assert.assertEquals(actualBackgroundPositionAfterChecked,"0px -85px");
        //--------------------------------------------------------------------------------------
        taskPage.clickOnSave();
        // Step 5: Accessing Created Task
        taskPage.pressViewTaskBtnPopUp();
        Thread.sleep(4000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'IFRAME_TYPE=SIDE_SLIDER')]")));
        // Step 6: Checking if High Priority enabled in Created Task
        WebElement switchLabel1 = driver.findElement(By.xpath("(//span[.='High Priority'])[2]"));
        String actualBckgrndPstnBeforeChecked = ((JavascriptExecutor)driver)
                .executeScript("return window.getComputedStyle(document.getElementsByClassName('if-not-no')[0], '::after').getPropertyValue('background-position');",switchLabel1).toString();
        Assert.assertEquals(actualBckgrndPstnBeforeChecked,"0px -122px");
    }
}
