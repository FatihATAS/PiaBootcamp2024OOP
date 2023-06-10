package utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementActions {
    WebDriver driver;
    public ElementActions(WebDriver driver){
        this.driver = driver;
    }
    public void clickElement(WebElement element){
        element.click();
    }
    public void sendKeysToElement(WebElement element , String type){
        element.sendKeys(type);
    }
    public void selectFromDropdownWithIndex(WebElement element , int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    public void moveToElement(WebElement webElement){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).moveToElement(webElement).perform();
    }
    public void selectFromDropdownWithValue(WebElement element , String value){
        new Select(element).selectByValue(value);
    }
    public void selectFromDropdownWithText(WebElement element , String text){
        new Select(element).selectByVisibleText(text);
    }
    public void scrollIntoElementWithJsExecutor(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();" , element);
    }
    public void scrollIntoElementWithActionsClass(WebElement element){
        new Actions(driver).scrollToElement(element).perform();
    }
    public void waitElementVisible(WebElement element){
        ReusableMethods.waitElementIsVisible(element , driver);
    }
}
