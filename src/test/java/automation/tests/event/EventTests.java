package automation.tests.event;

import automation.pages.login.LoginPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import org.testng.annotations.Test;

public class EventTests extends AbstractTestBase {

    /**
     * 2. User should be able to attach link by clicking on the link icon.
     */
    @Test
    public void verifyEventPage(){
        LoginPage lp=new LoginPage();
        lp.defaultLogin();
        lp.navigateOnTopMenu("Event");
        BrowserUtils.wait(5);
    }
}
