package UITests.Tests;

import UITests.BasePage.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

public class InsiderTest extends BasePage {
    @Test
    public void insiderTest() {
        driver.get(insiderUrl);
        Assert.assertEquals(driver.getCurrentUrl() , insiderUrl);
        allPages.homePage.clickAcceptCookiesButton();
        allPages.homePage.clickMoreIcon();
        allPages.homePage.clickCareersBtn();
        allPages.careersPage.checkAllBlocksOpened();
        allPages.careersPage.clickSalesBlock(); //See all teams button'a tıklanmadan farklı bir yöntemle çözüm yapılmıştır
        allPages.teamPage.clickSeeAllJobsButton();
        allPages.openPositionsPage.selectValueOfDepartment("Quality Assurance");
        allPages.openPositionsPage.selectValueOfLocation("Istanbul, Turkey");
        allPages.openPositionsPage.waitLastCardVisible();
        allPages.openPositionsPage.checkAllLocationTitleContains("Istanbul, Turkey");
        allPages.openPositionsPage.checkAllDepartmentTitlesContains("Quality Assurance");
        allPages.openPositionsPage.checkAllViewRoleButtonsIsDisplayed();
        allPages.openPositionsPage.clickViewRoleButtonIndexOf(2);
        ReusableMethods.goToNewTab(driver); //Açılan sekme yeni tab de açıldığı için driver yeni açılan sayfaya geçiş yapar
        Assert.assertTrue(driver.getCurrentUrl().contains("lever"));
    }
}
