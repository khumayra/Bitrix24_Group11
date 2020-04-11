package automation.pages.event;

import automation.pages.AbstractPageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EventPage extends AbstractPageBase {

    @FindBy(css = "[placeholder='Event name']")
    WebElement eventNameField;
    @FindBy(css = "#bx-html-editor-area-cnt-oCalEditorcal_3Jcl>div>iframe" )
    WebElement textAreaFrame;
    @FindBy(tagName = "body")
    WebElement textArea;
    @FindBy(css = "[id='bx-b-link-blogPostForm_calendar']>span")
    WebElement addLink;

    @FindBy(id = "linkoCalEditorcal_3Jcl-text")
    WebElement linkTextField;
    @FindBy(id = "linkoCalEditorcal_3Jcl-href")
    WebElement linkURLField;

    @FindBy(id="blog-submit-button-save")
    WebElement sendButton;


    public void setEventName(String eventName){
        wait.until(ExpectedConditions.visibilityOf(eventNameField)).sendKeys(eventName);
    }

    public void setEventText(String text){
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(textAreaFrame));
        driver.switchTo().frame(textAreaFrame);
        textArea.sendKeys(text);
        driver.switchTo().parentFrame();
    }

    public void addLinkToEvent(String linkText, String linkURL){
        wait.until(ExpectedConditions.elementToBeClickable(addLink)).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(linkTextField));
        linkTextField.sendKeys(linkText);
        linkURLField.sendKeys(linkURL+ Keys.ENTER);
    }

    public void saveEvent(){
        sendButton.click();
    }



}
