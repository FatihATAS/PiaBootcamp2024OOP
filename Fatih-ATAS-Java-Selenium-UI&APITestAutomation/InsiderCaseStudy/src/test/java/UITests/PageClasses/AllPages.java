package UITests.PageClasses;

import org.openqa.selenium.WebDriver;

public class AllPages {
    WebDriver driver;
    public HomePage homePage;
    public CareersPage careersPage;
    public TeamPage teamPage;
    public OpenPositionsPage openPositionsPage;
    public AllPages(WebDriver driver){
        this.driver = driver;
        homePage = new HomePage(this.driver);
        careersPage = new CareersPage(this.driver);
        teamPage = new TeamPage(this.driver);
        openPositionsPage = new OpenPositionsPage(this.driver);
    }

}
