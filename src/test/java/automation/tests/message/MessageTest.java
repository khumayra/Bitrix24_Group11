package automation.tests.message;

import automation.pages.login.LoginPage;
import automation.pages.message.MessagePage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import org.testng.annotations.Test;

public class MessageTest extends AbstractTestBase {
// User should be able to attach link by clicking on the link icon
    @Test
    public void attachLink() {
        LoginPage loginPage = new LoginPage();
        MessagePage messagePage = new MessagePage();

        loginPage.defaultLogin();
        messagePage.navigateOnTopMenu("Message");
        BrowserUtils.wait(3);
        messagePage.lnkButton();
        BrowserUtils.wait(3);
        messagePage.addLinkToMessage("https://cybertekschool.com/");
        BrowserUtils.wait(3);
        messagePage.send();
        BrowserUtils.wait(4);
    }

// User should be able to insert videos by clicking on the video icon and
// entering the video URL
    @Test
    public void insertVideo(){
        LoginPage loginPage = new LoginPage();
        MessagePage messagePage =new MessagePage();

        loginPage.defaultLogin();
        messagePage.navigateOnTopMenu("Message");
        messagePage.addVideo("https://www.youtube.com/watch?v=7F-8m_cbQoQ");
       BrowserUtils.wait(3);
        messagePage.send();
        BrowserUtils.wait(5);

    }

}
