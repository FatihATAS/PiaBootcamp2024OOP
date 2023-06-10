package UITests.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.ElementActions;

import java.util.ArrayList;
import java.util.List;

public class CareersPage {
    WebDriver driver;
    ElementActions elementActions;
    public CareersPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
        PageFactory.initElements(driver , this);
    }
    @FindBy(xpath = "//section//h2[text()='Life at Insider']")
    private WebElement lifeAtInsiderBlock;

    @FindBy(id = "career-our-location")
    private WebElement locationsBlock;

    @FindBy(id = "career-find-our-calling")
    private WebElement teamsBlock;
    /*
    Önemli NOT!
     "See all teams" butonunun aksiyon alınacak (click işlemi) yapılacak kısmı
     UI tarafında spesifik olarak TANIMLANMADIĞI için test adımlarında farklı bir yol
     izlenmiştir. Gerekli açıklamalar görüşme esnasında yapılacaktır.
    */
    @FindBy(css = ".btn-outline-secondary.loadmore")
    private WebElement seeAllTeamsButton;
    @FindBy(xpath = "//a/h3[text()='Quality Assurance']")
    private WebElement qualityAssuranceBlock;
    @FindBy(css = "img[alt='Sales']")
    private WebElement salesBlock;

    public void checkAllBlocksOpened(){
        List<WebElement> allBlocks = new ArrayList<>();
        allBlocks.add(lifeAtInsiderBlock);
        allBlocks.add(locationsBlock);
        allBlocks.add(teamsBlock);
        allBlocks.forEach(x->Assert.assertTrue(x.isDisplayed()));
    }
    public void clickSalesBlock(){
        elementActions.scrollIntoElementWithJsExecutor(salesBlock);
        elementActions.waitElementVisible(salesBlock);
        elementActions.clickElement(salesBlock);
    }
    public void clickSeeAllTeamsButton(){
        elementActions.scrollIntoElementWithJsExecutor(seeAllTeamsButton);
        elementActions.clickElement(seeAllTeamsButton);
    }
    public void clickQualityAssurance(){
        elementActions.clickElement(qualityAssuranceBlock);
    }

}
