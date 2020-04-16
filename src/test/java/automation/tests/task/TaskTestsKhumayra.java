package automation.tests.task;

import automation.pages.login.LoginPageKhumayra;
import automation.pages.task.TaskPageKhumayra;
import automation.tests.AbstractTestBaseKhumayra;
import automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class TaskTestsKhumayra extends AbstractTestBaseKhumayra {
    private By textEditorBarBy = By.xpath("//div[@id='bx-html-editor-tlbr-lifefeed_task_form']//span");


    @Test
    public void verifyHighPriorityChkBx () throws Exception{
        test = report.createTest("Selecting high priority checkbox make task top priority task");
        String titleValue = "Very Important Task";
        String descriptionValue = "Make it High Priority";
        // Step 1: Login to WebPage with default creditials
        LoginPageKhumayra loginPageKhumayra = new LoginPageKhumayra();
        loginPageKhumayra.login();
        // Step 2: Navigate to Task menu
        TaskPageKhumayra taskPage = new TaskPageKhumayra();
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
        String actualBckgrnAfterChecked = ((JavascriptExecutor)driver)
                .executeScript("return window.getComputedStyle(document.getElementsByClassName('if-not-no')[0], '::after').getPropertyValue('background-position');",switchLabel1).toString();
        Assert.assertEquals(actualBckgrnAfterChecked,"0px -122px");
        test.pass("High priority checkbox selection verified!");
    }

    @Test
    public void verifyVisibilityOfTextBar(){
        test = report.createTest("Verify visibility of text editor toolbar");
        LoginPageKhumayra loginPageKhumayra = new LoginPageKhumayra();
        loginPageKhumayra.login();
        TaskPageKhumayra taskPage = new TaskPageKhumayra();
        taskPage.navigateTo("Task");
        taskPage.makeEditorTextBarVisible();
        List<WebElement> textEditorBar = driver.findElements(textEditorBarBy);
        Assert.assertTrue(textEditorBar.size()>0);
        test.pass("Visibility of text editor toolbar verified!");
    }

    @Test
    public void verifyCreateQuote() {
        test = report.createTest("Verify creating the quote");
        LoginPageKhumayra loginPageKhumayra = new LoginPageKhumayra();
        loginPageKhumayra.login();
        TaskPageKhumayra taskPage = new TaskPageKhumayra();
        taskPage.navigateTo("Task");
        taskPage.enterTaskTitle("Some Title");
        taskPage.quoteBtnClick();

        //Assertion if quote button is clicked
        String actual = driver.findElement(By.xpath("//blockquote")).getAttribute("class");
        String expected = "bxhtmled-quote";
        Assert.assertEquals(actual,expected);
        String text = "Hello, World! "+ LocalDate.now();
        taskPage.sendBlockQuote(text);

        //Assertion if quote was created
        taskPage.sendButton();
        taskPage.pressViewTaskBtnPopUp();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src]")));
        BrowserUtils.wait(4);
        String actual1=driver.findElement(By.xpath(String.format("//td[text()='%s']",text))).getText();
        String expected1=text;
        Assert.assertEquals(actual1,expected1);
    }
}
