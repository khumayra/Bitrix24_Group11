package automation.tests;

import automation.pages.login.LoginPage;
import automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends AbstractTestBase {

    @Test
    public void validCredentialLogin(){
    test = report.createTest("Valid credential login");
        LoginPage loginPage = new LoginPage();
        // login as helpDesk employee
        loginPage.login();
        test.info("Login as HelpDesk employee");
        Assert.assertEquals(Driver.getDriver().getTitle(),"Portal");
        test.pass ("Page title Portal was verified");
    }

    @Test (dataProvider = "credentials")
    public void verifyCredentialsDDT (String username){
        test = report.createTest("Valid multiple credentials login");
        LoginPage longPage = new LoginPage();
        longPage.login(username);
        test.info(String.format("Login as %s employee",username));
        Assert.assertEquals(Driver.getDriver().getTitle(),"Portal");
        test.pass ("Page title Portal was verified");
    }

    @DataProvider (name = "credentials")
    public Object [] credentials (){
        return new Object[] {"helpdesk","marketing","hr"};
    }

    @Test (dataProvider = "invalid_credentials")
    public void invalidCredentialLogin (String username, String password){
       test = report.createTest(String.format("Log in with invalid credentials %s and %s",username,password));
       LoginPage loginPage = new LoginPage();
       loginPage.login(username, password);
       String expected = "Incorrect login or password";
       test.pass(expected);
    }

    @DataProvider (name = "invalid_credentials")
    public Object[][] invalidCredentials (){
        return new Object[][]{{"invalid_user_name", "UserUser"},{ "helpdesk45@cybertekschool.com","incorrect_password"}};
    }
}
