package automation.tests;

import automation.utilities.BrowserUtils;
import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public abstract class AbstractTestBase {
    protected WebDriverWait wait;   // Explicit wait
    protected Actions actions;

    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest test;

    @BeforeTest
    @Parameters("reportName")
    public void setupTest(@Optional String reportName){
        //System.out.println("reportName = " + reportName);
        reportName=reportName ==null ? "report.html" : reportName+".html";
        report=new ExtentReports();
        String reportPath=System.getProperty("user.dir")+"/test_output/"+reportName;
        //   System.out.println("reportPath = " + reportPath);
        htmlReporter=new ExtentHtmlReporter(reportPath);
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("Bitrix24 Test Automation Results");

    }

    @AfterTest
    public void afterTest(){
        report.flush();
    }

    @BeforeMethod
    public void setup(){
        String URL= ConfigurationReader.getProperty("url");
        System.out.println("URL = " + URL);
        //Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(URL);
        wait=new WebDriverWait(Driver.getDriver(),15);
        actions=new Actions(Driver.getDriver());
    }

    @AfterMethod
    public void teardown(ITestResult iTestResult) throws IOException {
        //ITestResult class describes the result of a test.
        //if test failed, take a screenshot
        //no failure - no screenshot
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            //screenshot will have a name of the test
            String screenshotPath = BrowserUtils.getScreenShot(iTestResult.getName());
            test.fail(iTestResult.getName());//attach test name that failed
            BrowserUtils.wait(2);
            test.addScreenCaptureFromPath(screenshotPath, "Failed");//attach screenshot
            test.fail(iTestResult.getThrowable());//attach console output
        }
        Driver.closeDriver();
    }

}