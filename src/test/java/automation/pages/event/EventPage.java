package automation.pages.event;

import automation.pages.AbstractPageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EventPage extends AbstractPageBase {

    @FindBy(css = "[placeholder='Event name']")
    private WebElement eventNameField;
    @FindBy(css = "#bx-html-editor-area-cnt-oCalEditorcal_3Jcl>div>iframe" )
    private WebElement textAreaFrame;
    @FindBy(tagName = "body")
    private WebElement textArea;
    @FindBy(css = "[id='bx-b-link-blogPostForm_calendar']>span")
    private WebElement addLink;


    @FindBy(css = "#bx-b-video-blogPostForm_calendar>span")
    private WebElement insertVideo;
    @FindBy(id="video_oCalEditorcal_3Jcl-source")
    private WebElement videoSourceField;
    @FindBy(id = "video_oCalEditorcal_3Jcl-title")
    private WebElement videoTitle;
    @FindBy(id = "undefined")
    private WebElement saveButton;


    @FindBy(id = "linkoCalEditorcal_3Jcl-text")
    private WebElement linkTextField;
    @FindBy(id = "linkoCalEditorcal_3Jcl-href")
    private WebElement linkURLField;
    @FindBy(id="blog-submit-button-save")
    private WebElement sendButton;


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
        wait.until(ExpectedConditions.visibilityOf(linkTextField));
        linkTextField.sendKeys(linkText);
        linkURLField.sendKeys(linkURL+ Keys.ENTER);
    }

    public void insertVideoToEvent(String videoURL){
        wait.until(ExpectedConditions.elementToBeClickable(insertVideo)).click();
        wait.until(ExpectedConditions.visibilityOf(videoSourceField)).sendKeys(videoURL);
        //wait.until(ExpectedConditions.visibilityOf(videoTitle));
        saveButton.click();

    }

    public void saveEvent(){
        sendButton.click();
    }



}