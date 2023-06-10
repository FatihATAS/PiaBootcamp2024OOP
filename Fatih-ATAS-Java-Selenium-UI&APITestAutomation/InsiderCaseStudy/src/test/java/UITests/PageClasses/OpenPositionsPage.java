package UITests.PageClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.ElementActions;
import utilities.ReusableMethods;

import java.util.List;
import java.util.Random;

public class OpenPositionsPage {
    WebDriver driver;
    ElementActions elementActions;
    public OpenPositionsPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
        PageFactory.initElements(driver , this);
    }
    @FindBy(id = "filter-by-location")
    private WebElement filterByLocationDropDown;
    @FindBy(id="filter-by-department")
    private WebElement filterByDepartmentDropDown;
    @FindBy(xpath = "(//div[text()='Istanbul, Turkey'])[3]")
    private WebElement lastIstanbulLocationTitle;
    @FindBy(css = "span.position-department")
    private List<WebElement> allPositionDepartmentTitles;
    @FindBy(css = ".position-location")
    private List<WebElement> allPositionLocationsTitles;
    @FindBy(css = "div.position-list-item-wrapper")
    private List<WebElement> allPositionList;
    @FindBy(xpath = "(//*[text()='View Role'])")
    private List<WebElement> allViewRoleButton;


    public void selectValueOfLocation(String location){
        elementActions.selectFromDropdownWithText(filterByLocationDropDown , location);
    }
    public void selectValueOfDepartment(String department){
        elementActions.selectFromDropdownWithText(filterByDepartmentDropDown , department);
    }
    public void checkAllDepartmentTitlesContains(String department){
        allPositionDepartmentTitles.forEach(x-> Assert.assertTrue(x.getText().contains(department)));
    }
    public void checkAllLocationTitleContains(String location){
       for(WebElement locationTitle : allPositionLocationsTitles){
           Assert.assertTrue(locationTitle.getText().contains(location));
       }
    }
    public void checkAllViewRoleButtonsIsDisplayed(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement firstRole = allPositionList.get(0);
        js.executeScript("arguments[0].scrollIntoView();" , firstRole);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).moveToElement(firstRole).perform();
        for(int i = 0; i<allPositionList.size(); i++){
            WebElement roleViewBtn = allViewRoleButton.get(i);
            actions.moveToElement(roleViewBtn).perform();
            Assert.assertTrue(roleViewBtn.isDisplayed());
        }
    }
    public void clickViewRoleButtonIndexOf(int index){
        WebElement firstRole = allPositionList.get(index);
        Actions actions = new Actions(driver);
        actions.moveToElement(firstRole).perform();
        WebElement roleViewBtn = allViewRoleButton.get(index);
        actions.moveToElement(roleViewBtn).click(roleViewBtn).perform();
    }
    public void clickViewRoleButtonRandom(){
        Random random = new Random();
        int randomNumber = random.nextInt(allPositionList.size());
        clickViewRoleButtonIndexOf(randomNumber);
    }
    public void waitLastCardVisible(){
        elementActions.waitElementVisible(lastIstanbulLocationTitle);
        ReusableMethods.waitElementIsVisible(lastIstanbulLocationTitle,driver);
    }
}
