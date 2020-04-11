package automation.tests.event;

import automation.pages.activitystream.ActivityStreamPage;
import automation.pages.event.EventPage;
import automation.pages.login.LoginPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EventTests extends AbstractTestBase {

    /**
     * User Story :
     *      3. As a user, I should be able to create
     *        events by clicking on Event tab under Activity Stream.
     * Acceptance Criteria:
     *      2. User should be able to attach link by clicking on the link icon.
     *
     *  Test steps;
     *      1- Login with HR credentials
     *      2- Go to the event page
     *      3- Create an event
     *      4- Add link to this event
     *      5- Save event
     *      6- Verify event is added to activity stream
     */
    @Test
    public void verifyEventCreateByLink(){
        LoginPage lp=new LoginPage();
        EventPage eventPage=new EventPage();
        ActivityStreamPage activityStreamPage=new ActivityStreamPage();
        lp.defaultLogin();
        eventPage.navigateOnTopMenu("Event");
        String eventTitle="Planning meeting";
        eventPage.setEventName(eventTitle);
        eventPage.setEventText("Sprint#21 planning meeting will cover .... \n");
        eventPage.addLinkToEvent("Spring planning info link","https://www.leadingagile.com/2012/08/simple-cheat-sheet-to-sprint-planning-meeting/");
        eventPage.saveEvent();
        Assert.assertEquals(activityStreamPage.latestEventTitle(),eventTitle);
        BrowserUtils.wait(5);
    }
}
