package pages;

import config.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;
    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void clickBySubmitButton(){
        submitButton.click();
    }
    public SearchPage addCity(String city){

        WebElement cityField = driver.findElement(By
                .xpath("//input[@id='city']")); // Находим поле ввода для местоположения на веб-странице с помощью XPath-выражения.
        cityField.clear(); //Очищаем поле ввода от предыдущего содержимого.
        cityField.sendKeys(city); // Вводим указанное местоположение в поле ввода.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='pac-container pac-logo']")));
        // Ожидаем, пока элемент <div> с классом "pac-item" станет видимым.
        // Этот элемент является элементом списка предложений, который появляется при вводе местоположения.
        // Нахождение всех элементов списка локаций

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='pac-item']")));
        List<WebElement> locationElements = driver.findElements(By.xpath("//div[@class='pac-item']"));
        System.out.println("SIZE : "+locationElements.size());
        // Выбор случайного элемента из списка

        Random random = new Random();
        WebElement randomLocation = locationElements.get(random.nextInt(locationElements.size()));

        // Клик по случайному элементу
        randomLocation.click();
        return this;
    }
    public SearchPage addDates(int periodInDays) {
        // Получаем текущую дату
        LocalDate currentDate = LocalDate.now();

        // Получаем дату через указанное количество дней
        LocalDate endDate = currentDate.plusDays(periodInDays);

        // Форматируем даты для поиска в календаре
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String startDateLabel = currentDate.format(formatter);
        String endDateLabel = endDate.format(formatter);

        // Находим поле ввода для дат
        WebElement datesInput = driver.findElement(By.id("dates"));
        datesInput.click(); // Кликаем на поле ввода для дат, чтобы открыть календарь

        // Ожидаем появления календаря
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement calendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//sat-calendar[@id='sat-datepicker-0']")));

        // Выбираем дату начала периода (текущая дата)
        WebElement startDateCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@aria-label, '" + startDateLabel + "')]")));
        startDateCell.click(); // Кликаем на ячейку с текущей датой

        // Выбираем дату конца периода (текущая дата + период в днях)
        WebElement endDateCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@aria-label, '" + endDateLabel + "')]")));
        endDateCell.click(); // Кликаем на ячейку с датой через период в днях
        return this;
    }

}