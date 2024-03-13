package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://ilcarro.web.app/");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    public void doSmth(){
        WebElement el = driver.findElement(By.xpath("//div[@class='header']//a[contains(text(),'Search')]"));
        el.click();
    }


}