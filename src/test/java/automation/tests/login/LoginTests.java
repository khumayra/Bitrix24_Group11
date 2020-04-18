package automation.tests.login;

import automation.pages.activitystream.ActivityStreamPageForEvent;
import automation.pages.login.LoginPage;
import automation.tests.AbstractTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests  extends AbstractTestBase {
    /**
     * Test default login option
     */
    @Test
    public void login(){
        LoginPage loginPage=new LoginPage();
        loginPage.defaultLogin();
    }

    @Test
    public void verifySuccessfulLogin(){
        LoginPage loginPage=new LoginPage();
        loginPage.defaultLogin();
        ActivityStreamPageForEvent page=new ActivityStreamPageForEvent();
        String expected="hr46@cybertekschool.com";
        Assert.assertEquals(page.getCurrentUserName(),expected,"user name is not matching");
    }

}
