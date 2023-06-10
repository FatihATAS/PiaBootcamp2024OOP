package UITests.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ElementActions;

public class TeamPage {
    WebDriver driver;
    ElementActions elementActions;
    public TeamPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
        PageFactory.initElements(driver , this);
    }
    @FindBy(css = "a.btn-outline-secondary")
    private WebElement seeAllJobsButton;
    public void clickSeeAllJobsButton(){
        elementActions.clickElement(seeAllJobsButton);
    }
}
