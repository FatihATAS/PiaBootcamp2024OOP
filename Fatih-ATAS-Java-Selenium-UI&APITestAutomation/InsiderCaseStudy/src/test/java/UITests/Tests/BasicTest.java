package UITests.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasicTest {
    @Test
    public void testName() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://useinsider.com/");
        Assert.assertTrue(driver.getCurrentUrl().contains("insider"));
        driver.findElement(By.id("wt-cli-accept-all-btn")).click();
        driver.findElement(By.xpath("(//*[@id='mega-menu-1'])[6]")).click();
        driver.findElement(By.xpath("//*[text()='Careers']")).click();
        WebElement careerBlock = driver.findElement(By.id("career-find-our-calling"));
        WebElement locationBlock = driver.findElement(By.id("career-our-location"));
        WebElement lifeAtInsiderBlock = driver.findElement(By.cssSelector("div[data-id='87842ec']"));
        List<WebElement> allBlocks = new ArrayList<>();
        allBlocks.add(careerBlock);
        allBlocks.add(locationBlock);
        allBlocks.add(lifeAtInsiderBlock);
        allBlocks.forEach(x->Assert.assertTrue(x.isDisplayed()));
        WebElement salesImg = driver.findElement(By.cssSelector("img[alt='Sales']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();" , salesImg);
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(salesImg)).click();
        driver.findElement(By.cssSelector("a.btn-outline-secondary")).click();
        //
        WebElement locationTab = driver.findElement(By.id("filter-by-location"));
        WebElement departmentTab = driver.findElement(By.name("filter-by-department"));
        Select locationSelect = new Select(locationTab);
        locationSelect.selectByIndex(0);
        locationSelect.selectByVisibleText("Istanbul, Turkey");
        Select departmentSelect = new Select(departmentTab);
        departmentSelect.selectByVisibleText("Quality Assurance");
        waitSecond();
        List<WebElement> positionDep = driver.findElements(By.xpath("//span[@class='position-department text-large font-weight-600 text-primary' and text()='Quality Assurance']"));
        positionDep.forEach(x->Assert.assertEquals(x.getText() , "Quality Assurance"));
        List<WebElement> locationInfos = driver.findElements(By.cssSelector(".position-location"));
        locationInfos.forEach(x->Assert.assertEquals(x.getText() , "Istanbul, Turkey"));


        WebElement firstRole = driver.findElement(By.xpath("(//*[@class='position-list-item-wrapper bg-light'])[1]"));
        js.executeScript("arguments[0].scrollIntoView();" , firstRole);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).moveToElement(firstRole).perform();
        List<WebElement> roleViewBtn = driver.findElements(By.xpath("(//*[text()='View Role'])"));
        for (int i = 0; i < roleViewBtn.size(); i++) {
            actions.moveToElement(roleViewBtn.get(i)).perform();
            Assert.assertTrue(roleViewBtn.get(i).isDisplayed());
        }
        actions.moveToElement(roleViewBtn.get(1)).click(roleViewBtn.get(1)).perform();
        String currentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> i = allWindowHandles.iterator();
        while (i.hasNext()){
            String newWindowHandle = i.next();
            if(!newWindowHandle.equals(currentWindowHandle)){
                driver.switchTo().window(newWindowHandle);
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("lever"));
        driver.quit();
    }
    public void waitSecond(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test2() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://useinsider.com/");
        Assert.assertTrue(driver.getCurrentUrl().contains("insider"));
        driver.findElement(By.id("wt-cli-accept-all-btn")).click();
        driver.findElement(By.xpath("(//*[@id='mega-menu-1'])[6]")).click();
        driver.findElement(By.xpath("//*[text()='Careers']")).click();
        WebElement careerBlock = driver.findElement(By.id("career-find-our-calling"));
        WebElement locationBlock = driver.findElement(By.id("career-our-location"));
        WebElement lifeAtInsiderBlock = driver.findElement(By.cssSelector("div[data-id='87842ec']"));
        List<WebElement> allBlocks = new ArrayList<>();
        allBlocks.add(careerBlock);
        allBlocks.add(locationBlock);
        allBlocks.add(lifeAtInsiderBlock);
        allBlocks.forEach(x->Assert.assertTrue(x.isDisplayed()));
        // See All Teams Click İşlemi!!!!
        WebElement seeAllTeamsBtn = driver.findElement(By.xpath("//a[text()='See all teams']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);" , seeAllTeamsBtn);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).click(seeAllTeamsBtn).perform();
        WebElement qaBtn = driver.findElement(By.xpath("//h3[text()='Quality Assurance']"));
        actions.scrollToElement(qaBtn).click().perform();

    }
}
