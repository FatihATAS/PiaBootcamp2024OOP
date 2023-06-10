package UITests.BasePage;
import UITests.PageClasses.AllPages;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;


public class BasePage {
    public String insiderUrl;
    public WebDriver driver;
    public AllPages allPages;

    @BeforeClass
    public void beforeClass() {
        insiderUrl = ConfigReader.getProperties("urlInsider");
    }

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
        allPages = new AllPages(driver);
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE){
            ReusableMethods.takeScreenShot(driver);
        }
        driver.quit();
    }
    @AfterClass
    public void afterClass(){

    }


}
