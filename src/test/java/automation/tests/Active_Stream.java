package automation.tests;

import automation.pages.login.LoginPage;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Active_Stream extends AbstractTestBase {

    static WebDriver driver  = Driver.getDriver();
    LoginPage loginPage = new LoginPage();

    @Test
    public void activeStream() {
        test = report.createTest("Verify Activity Stream and Save Filter ");
        loginPage.defaultLogin();
        BrowserUtils.wait(3);
        driver.findElement(By.id("LIVEFEED_search")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//*[@id=\"popup-window-content-LIVEFEED_search_container\"]/div/div/div[2]/div[1]/div[1]/div[1]")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.name("CREATED_BY_ID_label")).sendKeys("hr46@cybertekschool.com");
        BrowserUtils.wait(2);
        driver.findElement(By.name("TO_label")).sendKeys("All employees");
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//*[@id=\"popup-window-content-LIVEFEED_search_container\"]/div/div/div[3]/div[2]/div/button")).click();
        BrowserUtils.wait(4);
        driver.findElement(By.id("LIVEFEED_search")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//*[@id=\"popup-window-content-LIVEFEED_search_container\"]/div/div/div[3]/div[1]/span[1]")).click();
        BrowserUtils.wait(3);
        driver.findElement(By.xpath("//*[@id=\"popup-window-content-LIVEFEED_search_container\"]/div/div/div[3]/div[3]/div/span[1]")).click();
        BrowserUtils.wait(3);
    }
}




