package tests;

import config.BaseTest;
import org.testng.annotations.Test;
import pages.*;

public class TestClass extends BaseTest {

    @Test
    public void test001Master() throws InterruptedException {
        HomePage hp = new HomePage(getDriver());
        LogInPage logInPage = hp.openTopMenu(TopMenuItem.LOGIN);
        logInPage.tryToFindAnElement();
        LetTheCarWorkPage lcwp = logInPage.openTopMenu(TopMenuItem.LET_THE_CAR_WORK);
        lcwp.setManufacturer("BMW").addLocation("Montana")
                .setFuelType().photoAttachment("C:\\Pictures\\BlueCar.jpg");


        Thread.sleep(3000);
    }
    @Test
    public void findYourCarBranch2() throws InterruptedException {
        HomePage hp = new HomePage(getDriver());
        SearchPage searchPage = hp.openTopMenu(TopMenuItem.SEARCH);
        searchPage.addCity("Moscow").addDates(5);
        Thread.sleep(3000);
    }
}
