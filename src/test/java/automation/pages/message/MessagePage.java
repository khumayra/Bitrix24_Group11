package automation.pages.message;

import automation.pages.AbstractPageBase;
import automation.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagePage extends AbstractPageBase {


    @FindBy(id=("feed-add-post-form-tab-message"))
    private WebElement messageTab;

    @FindBy(xpath = "//span[contains(@ title,'Link')]")
    private WebElement linkBtn;

    @FindBy(xpath = "//input[contains(@ placeholder,'Link URL')]")
    private WebElement linkURLText;

    @FindBy(id = "undefined")
    private WebElement saveButton;

    @FindBy(id = "blog-submit-button-save")
    private WebElement sendBtn;

    @FindBy (xpath = "//span[contains(@ title,'Insert video')]")
    private WebElement videoBtn;

    @FindBy(id = "video_idPostFormLHE_blogPostForm-source")
    private WebElement videoSource;




    public void lnkButton(){
        linkBtn.click();
    }

    public void addLinkToMessage(String linkURl){
        linkURLText.sendKeys(linkURl);
        BrowserUtils.wait(3);
        saveButton.click();
    }
    public void send(){
        sendBtn.click();
    }

    public void addVideo(String videoURL){
       BrowserUtils.wait(3);
        videoBtn.click();
        videoSource.sendKeys(videoURL);
        saveButton.click();
    }











}
