package automation.pages.activitystream;

import automation.pages.AbstractPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ActivityStreamPageForEvent extends AbstractPageBase {

    @FindBy(xpath = "(//div[starts-with(@id,'feed-post-text-contentview-CALENDAR_EVENT')])[1]//a[contains(@id,'feed-event-view-link')]")
    private WebElement latestEventLink;

    @FindBy(xpath = "(//div[starts-with(@id,'feed-post-text-contentview-CALENDAR_EVENT')])[1]//iframe")
    private WebElement latestEventVideoFrame;

    public String latestEventTitle(){
        return latestEventLink.getText();
    }

    public String latestEventVideoLink(){
        wait.until(ExpectedConditions.visibilityOf(latestEventVideoFrame));
        return latestEventVideoFrame.getAttribute("src").trim();
    }

}