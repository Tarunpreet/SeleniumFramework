package EcommerceWeb.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    //Generic function-> Wait,switches,alert
    //only initalized using PageClasses(childs), So using super(s) as this class also needs driver
    WebDriver driver;
    public BasePage(WebDriver driver)
    {
        this.driver=driver;
    }
    public void waitForElementToBeVisible(WebElement element)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToBeClickable(WebElement element)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable((element)));
    }
    public void waitForElementToBeVisible(By locator)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitForElementToBeClickable(By locator)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitForElementToBeLocated(By locator)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
