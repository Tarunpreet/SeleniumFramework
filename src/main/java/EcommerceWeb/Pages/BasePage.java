package EcommerceWeb.Pages;

import EcommerceWeb.DriverUtil.PageUtils;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage extends PageUtils {
    //Entities which are common to all or some pages
    //only initalized using PageClasses(childs), So using super(s) in them as this class also needs driver
    WebDriver driver;
    public BasePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }

    //Header objects
    @FindBy(xpath = "//li[@id='menu-item-1227'] //a[text()='Store']")
    WebElement storeHeader;
    @FindBy(xpath = "//li[contains(@id,'menu-item')] //a[text()='Men']")
    WebElement menHeader;
    @FindBy(xpath = "//li[contains(@id,'menu-item')] //a[text()='Women']")
    WebElement womenHeader;
    @FindBy(xpath = "//li[contains(@id,'menu-item')] //a[text()='Accessories']")
    WebElement accessoriesHeader;
    @FindBy(xpath = "//li[contains(@id,'menu-item')] //a[text()='Account']")
    WebElement accountHeader;
    @FindBy(xpath = "//li[contains(@id,'menu-item')] //a[text()='About']")
    WebElement aboutHeader;
    @FindBy(xpath = "//li[contains(@id,'menu-item')] //a[text()='Contact Us']")
    WebElement contactHeader;

    public void startApplication()
    {
        driver.get("https://askomdch.com/");
    }

    //Loading a specific page
    public void load(String endPoint) {
        driver.get("https://askomdch.com/" +endPoint);
    }



}
