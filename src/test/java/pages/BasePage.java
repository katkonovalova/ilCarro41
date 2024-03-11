package pages;

import config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// BasePage служит базовым классом для всех страниц тестового приложения
public class BasePage {

    protected WebDriver driver;
    public BasePage(WebDriver webDriver){ // Метод устанавливает значение поля driver в переданный экземпляр веб-драйвера.
        this.driver=webDriver; //Эта строка присваивает переданный экземпляр веб-драйвера переменной driver, что позволяет другим классам иметь доступ к этому веб-драйверу через метод getDriver()
    }

    public static void setDriver(WebDriver driver) {
    }

    public <T extends BaseTest> T openTopMenu(TopMenuItemPage topMenuItemPage){
        String menuItemName = topMenuItemPage.getLabel();
        WebElement menuItem = driver.findElement(By.xpath("//div[class='header']//a[contains(text(),'" + menuItemName + "')]"));
        menuItem.click();

        switch (topMenuItemPage){
            case SEARCH:
                return (T) new SearchPage(driver);
            case TERMS_OF_USE:
                return (T) new TermOfUsePage(driver);
            case LOGIN:
                return (T) new LoginPage(driver);
            case SIGN_UP:
                return (T) new SignUpPage(driver);
            case LET_THE_CAR_WORK:
                return (T) new LetTheCarWorkPage(driver);
            default:
                throw new IllegalArgumentException("wrong " + topMenuItemPage);
        }
    }
}
