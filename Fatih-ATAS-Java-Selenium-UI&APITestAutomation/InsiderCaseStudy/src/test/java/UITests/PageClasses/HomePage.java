package UITests.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ElementActions;

public class HomePage {
    WebDriver driver;
    ElementActions elementActions;
    public HomePage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
        PageFactory.initElements(driver , this);
    }
    @FindBy (xpath = "(//*[@id='mega-menu-1'])[6]")
    private WebElement moreIcon;
    @FindBy (xpath = "//h5[text()='Careers']/..")
    private WebElement careersButton;
    @FindBy(id = "wt-cli-accept-all-btn")
    private WebElement acceptCookiesBtn;
    public void clickMoreIcon(){
        elementActions.clickElement(moreIcon);
    }
    public void clickCareersBtn(){
        elementActions.clickElement(careersButton);
    }
    public  void clickAcceptCookiesButton(){
        elementActions.clickElement(acceptCookiesBtn);
    }
}
