package automation.tests.task;

import automation.pages.login.LoginPage;
import automation.pages.task.TaskPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TaskTests extends AbstractTestBase {
    private By textEditorBarBy = By.xpath("//div[@id='bx-html-editor-tlbr-lifefeed_task_form']//span");
    private By streamTimeBy = By.className("feed-time");

    @Test
    public void verifyHighPriorityChkBx () {
        test = report.createTest("Selecting high priority checkbox make task top priority task");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        TaskPage taskPage = new TaskPage();
        taskPage.navigateTo("Task");
        BrowserUtils.wait(4);
        WebElement switchLabel = driver.findElement(By.xpath("//label[.='High Priority']"));
        String actualBackgroundPositionBeforeChecked = ((JavascriptExecutor)driver)
                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');",switchLabel).toString();
        Assert.assertEquals(actualBackgroundPositionBeforeChecked,"0px -103px");
        taskPage.selectHighPriority();
        String actualBackgroundPositionAfterChecked = ((JavascriptExecutor)driver)
                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');",switchLabel).toString();
        Assert.assertEquals(actualBackgroundPositionAfterChecked,"0px -85px");

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
