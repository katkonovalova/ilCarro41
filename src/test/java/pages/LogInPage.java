package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class LogInPage extends BasePage{
    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void tryToFindAnElement(){
        List<WebElement> list = driver.findElements(By.xpath("//a"));
        System.out.println("SIZE : " +list.size());
    }
}