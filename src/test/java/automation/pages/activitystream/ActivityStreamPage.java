package automation.pages.activitystream;

import automation.pages.AbstractPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ActivityStreamPage extends AbstractPageBase {

    @FindBy(xpath = "(//div[starts-with(@id,'feed-post-text-contentview-CALENDAR_EVENT')])[1]//a[contains(@id,'feed-event-view-link')]")
    WebElement latestEventLink;

    public String latestEventTitle(){
        return latestEventLink.getText();
    }

}
