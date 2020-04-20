package automation.tests.event;

import automation.pages.activitystream.ActivityStreamPageForEvent;
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
     * 6. User should be able to click on Visual Editor and see the editor text-bar displays on top of the message box.
     * 7. User should be able to add Event start and ending date and time, and specify the time zone.
     * 8. User should be able to set reminder by entering the timeing.
     * 9. User should be able to select event location from dropdown.
     * 10. User should be able to add attendees by selecting contacts individually or adding grups and departments.
     * 11. Useer should be able to click on More to specify the event details.
     *  Test steps;
     *      1- Login with HR credentials
     *      2- Go to the event page
     *      3- Create an event
     *      4- Add link to this event
     *      5- Save event
     *      6- Verify event is added to activity stream
     */
    @Test
    public void verifyLinkAddedToEvent(){
        LoginPage lp=new LoginPage();
        EventPage eventPage=new EventPage();
        ActivityStreamPageForEvent activityStreamPageForEvent =new ActivityStreamPageForEvent();
        lp.defaultLogin();
        eventPage.navigateOnTopMenu("Event");
        String eventTitle="Planning meeting";
        eventPage.setEventName(eventTitle);
        eventPage.setEventText("Sprint#21 planning meeting will cover .... \n");
        eventPage.addLinkToEvent("Spring planning info link","https://www.leadingagile.com/2012/08/simple-cheat-sheet-to-sprint-planning-meeting/");
        eventPage.saveEvent();
        Assert.assertEquals(activityStreamPageForEvent.latestEventTitle(),eventTitle);
        BrowserUtils.wait(5);
    }

    /**
     * User Story :
     *  3. As a user, I should be able to create events by clicking on Event tab under Activity Stream.
     *
     *  Acceptance Criteria:
     *   4. User should be able to insert videos by clicking on the video icon and entering the video URL.
     *
     */
    @Test
    public void verifyVideoInsertedToEvent(){
        LoginPage lp=new LoginPage();
        EventPage eventPage=new EventPage();
        ActivityStreamPageForEvent activityStreamPageForEvent =new ActivityStreamPageForEvent();

        lp.defaultLogin();
        eventPage.navigateOnTopMenu("Event");
        String eventTitle="Video meeting";
        eventPage.setEventName(eventTitle);
        eventPage.setEventText("Video meeting video link is attached \n");
        String videoURL="https://www.youtube.com/watch?v=Ch_hoYPPeGc";
        eventPage.insertVideoToEvent(videoURL);
        eventPage.saveEvent();
        Assert.assertEquals(activityStreamPageForEvent.latestEventTitle(),eventTitle);
        //Assert.assertTrue(activityStreamPageForEvent.latestEventVideoLink().contains(videoURL));
        BrowserUtils.wait(5);


    }
}