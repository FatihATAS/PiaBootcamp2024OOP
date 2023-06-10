package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class ReusableMethods {
    static int durationTime = Integer.parseInt(ConfigReader.getProperties("durationOfSecondsTime"));
    public static void waitElementIsVisible(WebElement webElement , WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationTime));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public static void takeScreenShot(WebDriver driver) {
        LocalDateTime currentTime = LocalDateTime.now();
        int index = currentTime.toString().indexOf('.');
        String formattedDate = currentTime.toString().substring(0,index).replace(':' , '-');
        String screenShotFileName = ConfigReader.getProperties("qaNameSurname") + formattedDate;
        File screenShots = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShots , new File("src/test/test-outputs/"+screenShotFileName+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void goToNewTab(WebDriver driver){
        String currentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> i = allWindowHandles.iterator();
        while (i.hasNext()){
            String newWindowHandle = i.next();
            if(!newWindowHandle.equals(currentWindowHandle)){
                driver.switchTo().window(newWindowHandle);
            }
        }
    }

}
