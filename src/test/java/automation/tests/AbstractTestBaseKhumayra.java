package automation.tests;

import automation.utilities.BrowserUtils;
import automation.utilities.ConfigurationReader;
import automation.utilities.DateTimeUtilities;
import automation.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Date;

public abstract class AbstractTestBaseKhumayra {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest test;

    @BeforeTest
    public void setupTest(){

        String date = new Date().toString().replace(" ", "_").replace(":", "-");
        report = new ExtentReports();
        String reportPath = "";
        if (System.getProperty("os.name").toLowerCase().contains("win")){
            reportPath = System.getProperty("user.dir")+"\\test-output\\"+date+"_report.html";
        } else {
            reportPath = System.getProperty("user.dir")+"/test-output/"+date+"_report.html";
        }
        htmlReporter = new ExtentHtmlReporter(reportPath);
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("Bitrix 24 Test Automation Results");
    }

    @AfterTest
    public void afterTest(){
        report.flush();
    }

    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver();
        driver.get(ConfigurationReader.getProperty("url"));
        driver.manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(),15);
    }

    @AfterMethod
    public void teardown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            String screenshotLocation = BrowserUtils.getScreenShot(testResult.getName());
            try {
                test.fail(testResult.getName());//test name that failed
                test.addScreenCaptureFromPath(screenshotLocation);//screenshot as an evidence
                test.fail(testResult.getThrowable());//error message
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to attach screenshot");
            }
        }else if(testResult.getStatus() == ITestResult.SUCCESS){
            test.pass(testResult.getName());
        }else if(testResult.getStatus() == ITestResult.SKIP){
            test.skip(testResult.getName());
        }
        BrowserUtils.wait(3);
        Driver.closeDriver();
    }

}
