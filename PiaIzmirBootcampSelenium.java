import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class PiaIzmirBootcampSelenium {


    @Test
    public void locators() {
        // 20 DK
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement name = driver.findElement(By.id("name")); //id ile element bulma
        name.sendKeys("FatihATAS");
        WebElement email = driver.findElement(By.xpath("//input[@id='email']")); //xpath ile element bulma
        email.sendKeys("fatihatas@gmail.com");
        WebElement phone = driver.findElement(By.cssSelector("#phone.form-control")); //css selector (className ve id ile)
        phone.sendKeys("05061147762");
        WebElement adress = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[4]/div[1]/div/div/div[1]/div[1]/div/div/div/div/div[2]/div[2]/textarea"));
        //Full xpath ile element bulma (Burada niçin full xpath kullanılmaması gerektiği anlatılacak)
        /*
        Geliştirme aşamasındaki bir uygulamada elementlerin yeri sürekli değişiklik gösterebilir.
        Biz örnek olarak "adress" alanının full xpath'ini aldık, ancak diyelim ki sayfada bir değişiklik yaptığımızda
        element için verdiğimiz locator değişecektir ve hataya & farklı bir alanda işlem yaptığından dolayı
        testimizi yanlış kontrol etmesine sebep olabilir. (false negative :) )
        */
        adress.sendKeys("Erzincan / Merkez");
        driver.close();
    }


    @Test
    public void driverOperations() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        Thread.sleep(1000);
        driver.navigate().to("https://www.mynet.com/");
        driver.navigate().back(); //Tekrar Google'a geldi
        Assertion assertion = new Assertion();
        assertion.assertTrue(driver.getTitle().contains("Google")); //Google olduğunu doğruladık
        Thread.sleep(1000);
        driver.navigate().forward(); //Mynet'e gittik
        assertion.assertTrue(driver.getTitle().contains("Mynet")); //Mynette olduğunu doğruladık
        driver.navigate().refresh();
        driver.close();
    }

    @Test
    public void SelectDropdownClass() throws InterruptedException {

        //20 DK
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement countries = driver.findElement(By.id("country"));
        Select select = new Select(countries);
        select.selectByIndex(5);
        Thread.sleep(1500);
        select.selectByValue("uk");
        Thread.sleep(1500);
        select.selectByVisibleText("United States");
        Thread.sleep(1500);

        WebElement colors = driver.findElement(By.xpath("//select[@id='colors' and @class='form-control']"));
        // xpath' de farklı bir gösterim yapıldı

        //Multiple select uygulaması
        Select selectColors = new Select(colors);
        selectColors.selectByIndex(2);
        selectColors.selectByIndex(3);
        Thread.sleep(1500);
        selectColors.deselectByValue("green"); //deselect ile seçilen option'ın kaldırılması
        Thread.sleep(1500);
        driver.close();
    }

    @Test
    public void WindowHandles() throws InterruptedException {
        //30 DK
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement newWindowsButton = driver.findElement(By.xpath("//button[text()='New Browser Window']"));
        // Bir WebElementin text'i ile bulma uygulaması yapıldı

        //Uzun yol ile yeni açılan sekmeye driver'ı set etme
        String anaSayfaIdsi = driver.getWindowHandle();
        newWindowsButton.click();
        Set<String> allWindowHandles = driver.getWindowHandles();
        // iterator'da kullanabilirsiniz.

        /*
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()){
            String windowHandle = iterator.next();
            if(!windowHandle.equals(anaSayfaIdsi)){
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        */
        for(String item : allWindowHandles){
            if(!item.equals(anaSayfaIdsi)){
                driver.switchTo().window(item);
                break;
            }
        }
        WebElement searchArea = driver.findElement(By.name("search"));
        searchArea.sendKeys("Çok şükür bir sonraki sayfaya geçtik :D ");
        Thread.sleep(1500);
        // Ana sayfaya dönerek herhangi bir işlem yaptırılır
        driver.switchTo().window(anaSayfaIdsi);
        WebElement nameArea = driver.findElement(By.id("name"));
        nameArea.sendKeys("Deminden şimdiye geldim");
        Thread.sleep(1500);
        //driver.close();
        driver.quit(); //Burada driver close ile driver quit farkı gösterilecek



    }

    @Test
    public void kisaYolIleWindowHandle() throws InterruptedException {
        // 10 DK
        // Kısa yol ile yeni açılan sekmeye gitme
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://fizy.com/");
        WebElement fastLoginButton = driver.findElement(By.cssSelector(".login .fastLogin#webPlayerHeader"));
        // Burada CSS selector'un detaylı bir gösterimi olacak
        fastLoginButton.click();
        Thread.sleep(2000);
        ArrayList<String> allWindowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(allWindowHandles.get(1)); //Yeni açılan sayfaya gittik
        WebElement phoneArea = driver.findElement(By.id("phoneNo"));
        phoneArea.sendKeys("5061147762");
        WebElement hizliGirisSifresiCheckBox = driver.findElement(By.xpath("(//label[@for='loginWithPassword'])[1]"));
        // Xpath locator'da indexleme uygulaması yapıldı
        hizliGirisSifresiCheckBox.click();
        Thread.sleep(1500);
        driver.switchTo().window(allWindowHandles.get(0)); //ana sayfaya geçtik
        WebElement nedenPremiumButton = driver.findElement(By.id("membershipsHeader"));
        nedenPremiumButton.click();
        Thread.sleep(1500);
        driver.quit();
    }

    @Test
    public void alerts() throws InterruptedException {
        // 25 DK
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        // JS Alert
        WebElement alertButton = driver.findElement(By.xpath("//button[@onclick='myFunctionAlert()']"));
        alertButton.click();
        Thread.sleep(1500);
        driver.switchTo().alert().accept();
        WebElement confirmAlert = driver.findElement(By.xpath("//button[@onclick='myFunctionConfirm()']"));
        confirmAlert.click();
        driver.switchTo().alert().dismiss(); // Dismiss yaptım
        Thread.sleep(1500);
        WebElement promptAlert = driver.findElement(By.xpath("//button[@onclick='myFunctionPrompt()']"));
        promptAlert.click();
        driver.switchTo().alert().sendKeys("Merhaba İzmir Kampı");
        driver.switchTo().alert().accept();
        Thread.sleep(1500);
        driver.close();
    }

    @Test
    public void HardAssertions() {
        //15 DK
        //Hard assertion - Burada yanlış doğrulama yapılarak kod kırılımı yaptığı gösterilecek
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement promptAlert = driver.findElement(By.xpath("//button[@onclick='myFunctionPrompt()']"));
        promptAlert.click();
        driver.switchTo().alert().sendKeys("Merhaba İzmir Kampı");
        driver.switchTo().alert().accept();
        WebElement alertText = driver.findElement(By.cssSelector("p#demo"));
        Assertion assertion = new Assertion();
        assertion.assertTrue(alertText.isDisplayed());
        assertion.assertEquals(alertText.getText() , "Hello Merhaba İzmir Kampı! How are you today?");
        assertion.assertTrue(alertText.getText().contains("Merhaba İzmir"));
        driver.close();
    }

    @Test
    public void SoftAssertions() {
        // 15 DK
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement promptAlert = driver.findElement(By.xpath("//button[@onclick='myFunctionPrompt()']"));
        promptAlert.click();
        driver.switchTo().alert().sendKeys("Merhaba İzmir Kampı");
        driver.switchTo().alert().accept();
        WebElement alertText = driver.findElement(By.cssSelector("p#demo"));
        SoftAssert softAssert = new SoftAssert(); //Soft assertion tanımı
        softAssert.assertTrue(alertText.isDisplayed());
        softAssert.assertTrue(alertText.getText().contains("Erzincan") , "Şöyle bir hata aldım kardeş...");
        driver.close();
        softAssert.assertAll(); //Driver kapandı ve hata mesajımızı loglara yazdı
    }

    @Test
    public void actionsClass_DoubleClick() throws InterruptedException {
        // 10DK
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement field1 = driver.findElement(By.id("field1"));
        field1.clear();
        field1.sendKeys("PiaBootcamp2024");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[@ondblclick='myFunction1()']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickButton).perform();
        Thread.sleep(1000);
        driver.close();
    }

    @Test
    public void actions_DragAndDrop() throws InterruptedException {
        //10 DK
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement dragEdilecekKutu = driver.findElement(By.cssSelector("div#draggable"));
        WebElement dropEdilecekKutu = driver.findElement(By.cssSelector("div#droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragEdilecekKutu , dropEdilecekKutu).perform();
        Thread.sleep(1500);
        driver.close();
    }

    @Test
    public void deneme() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        driver.get("https://testautomationpractice.blogspot.com/");
        WebElement field1 = driver.findElement(By.id("field1"));
        field1.clear();
        field1.sendKeys("dsaldssdsadsadsadsasdadsdsadsadsa");
        Thread.sleep(1500);
        driver.close();

    }
    @Test
    public void screenShotsBootcamp() throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshot.png");
        FileUtils.copyFile(srcFile, destFile);
        driver.close();
    }
}

