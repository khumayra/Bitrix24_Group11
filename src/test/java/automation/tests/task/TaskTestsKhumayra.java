package automation.tests.task;

import automation.pages.login.LoginPage;
import automation.pages.task.TaskPageKhumayra;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class TaskTestsKhumayra extends AbstractTestBase {

    @Test
    public void verifyHighPriorityChkBx () throws Exception{
        test = report.createTest("Selecting high priority checkbox make task top priority task");
        String titleValue = "Very Important Task";
        String descriptionValue = "Make it High Priority";
        // Step 1: Login to WebPage with default creditials
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsHelpDesk();
        // Step 2: Navigate to Task menu
        TaskPageKhumayra taskPage = new TaskPageKhumayra();
        taskPage.navigateOnTopMenu("Task");

        // Step3: Filling out task form
        taskPage.enterTaskTitle(titleValue);
        taskPage.enterTaskDescription(descriptionValue);
        Thread.sleep(4000);
        //--------------------------------------------------------------------------------------
        // Step 4: Checking if High Priority selected in New Task
//        WebElement switchLabel = driver.findElement(By.xpath("//label[.='High Priority']"));
//        String actualBackgroundPositionBeforeChecked = ((JavascriptExecutor)driver)
//                .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');",switchLabel).toString();
//        Assert.assertEquals(actualBackgroundPositionBeforeChecked,"0px -103px");
        WebElement switchLabel=taskPage.setSwitchLabel();
        taskPage.selectHighPriority();
        String actualBackgroundPositionAfterChecked = taskPage.setActualBackgroundPositionAfterChecked();
        Assert.assertEquals(actualBackgroundPositionAfterChecked,"0px -85px");
        //--------------------------------------------------------------------------------------
        taskPage.clickOnSave();
        // Step 5: Accessing Created Task
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='View task']")));
        Driver.getDriver().switchTo().activeElement();
        taskPage.pressViewTaskBtnPopUp();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'IFRAME_TYPE=SIDE_SLIDER')]")));
        //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'IFRAME_TYPE=SIDE_SLIDER')]")));
        // Step 6: Checking if High Priority enabled in Created Task
        WebElement switchLabel1 = taskPage.setSwitchLabel1();
        String actualBckgrnAfterChecked = taskPage.checkPositionInExistingTask();
        Assert.assertEquals(actualBckgrnAfterChecked,"0px -122px");
        test.pass("High priority checkbox selection verified!");
    }

    @Test
    public void verifyVisibilityOfTextBar(){
        test = report.createTest("Verify visibility of text editor toolbar");
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsHelpDesk();
        TaskPageKhumayra taskPage = new TaskPageKhumayra();
        taskPage.navigateOnTopMenu("Task");
        taskPage.makeEditorTextBarVisible();
        List<WebElement> textEditorBar = taskPage.getTextEditorBarElements();
        Assert.assertTrue(textEditorBar.size()>0);
        test.pass("Visibility of text editor toolbar verified!");
    }

    @Test
    public void verifyCreateQuote() {
        test = report.createTest("Verify creating the quote");
        LoginPage loginPage = new LoginPage();
        loginPage.loginAsHelpDesk();
        TaskPageKhumayra taskPage = new TaskPageKhumayra();
        taskPage.navigateOnTopMenu("Task");
        taskPage.enterTaskTitle("Some Title");
        taskPage.quoteBtnClick();

        //Assertion if quote button is clicked
        String actual = taskPage.getClassAttributeQuoteSelected();
        String expected = "bxhtmled-quote";
        Assert.assertEquals(actual,expected);
        String text = "Hello, World! "+ LocalDate.now();
        taskPage.sendBlockQuote(text);

        //Assertion if quote was created
        taskPage.sendButton();
        taskPage.pressViewTaskBtnPopUp();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src]")));
        //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src]")));
        BrowserUtils.wait(4);
        String expected1=text;
        String actual1 = taskPage.getQuoteText(text);
        Assert.assertEquals(actual1,expected1);
    }
}
