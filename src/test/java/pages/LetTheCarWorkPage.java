package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class LetTheCarWorkPage extends BasePage{
    public LetTheCarWorkPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public LetTheCarWorkPage setManufacturer(String manufacturer){
        WebElement manufacturerField = driver
                .findElement(By.xpath("//input[@id='make']"));
        manufacturerField.sendKeys(manufacturer);
        return this;
    }
    // Метод предназначен для добавления местоположения в поле ввода на странице.
    public LetTheCarWorkPage addLocation(String location){

        WebElement locationField = driver.findElement(By
                .xpath("//input[@id='pickUpPlace']")); // Находим поле ввода для местоположения на веб-странице с помощью XPath-выражения.
        locationField.clear(); //Очищаем поле ввода от предыдущего содержимого.
        locationField.sendKeys(location); // Вводим указанное местоположение в поле ввода.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[@class='pac-item']")));
        // Ожидаем, пока элемент <div> с классом "pac-item" станет видимым.
        // Этот элемент является элементом списка предложений, который появляется при вводе местоположения.
        element.click(); // выбираем этот элемент.
        return this;
    }

    public LetTheCarWorkPage photoAttachment(String filePath){
        //метод находит элемент на странице, который представляет собой поле ввода для загрузки фотографий.
        WebElement addPhoto = driver.findElement(By.id("photos"));
        /*
        JavascriptExecutor - это интерфейс, предоставляемый WebDriver,
            который позволяет выполнить JavaScript код в контексте браузера.
        * */
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView(true);", addPhoto); // Метод scrollIntoView(true) прокручивает страницу так, чтобы элемент стал видимым.
        addPhoto.sendKeys(filePath); // используем метод sendKeys для отправки пути к файлу (filePath) на элемент (в данном случае, это поле ввода для загрузки фотографий).
        return  this;
    }

    /**
     * Метод предназначен для выбора случайного типа топлива в выпадающем списке на веб-странице
     * @return LetTheCarWorkPage
     */
    public LetTheCarWorkPage setFuelType(){
        WebElement fuelSelect = driver
                .findElement(By.xpath("//select[@id='fuel']")); // находим выпадающий список на веб-странице с помощью XPath-выражения,
        // которое ищет элемент с атрибутом id, равным "fuel".
        // Полученный элемент представляет собой элемент <select>.
        Select select = new Select(fuelSelect); // Создаем объект класса Select, который предоставляет методы для взаимодействия с выпадающим списком
        List<WebElement> options = select.getOptions(); // Получаем список всех вариантов выбора в выпадающем списке.
        Random random = new Random();
        int index = random.nextInt(options.size());// Генерируем случайный индекс, который будет представлять номер варианта выбора в списке. Этот индекс выбирается из диапазона от
        // 0 (включительно) до количества вариантов выбора в списке
        select.selectByIndex(index); // Выбираем вариант выбора по индексу, который мы сгенерировали ранее.
        return this; //  Возвращаем текущий экземпляр страницы, чтобы обеспечить возможность цепочного вызова методов.
    }
}