package automation.pages.event;

import automation.pages.AbstractPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EventPage extends AbstractPageBase {

    @FindBy(css = "[placeholder='Event name']")
    WebElement eventName;

}
