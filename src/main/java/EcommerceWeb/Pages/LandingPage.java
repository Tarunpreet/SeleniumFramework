package EcommerceWeb.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    WebDriver driver;


    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='wp-block-button']")
    WebElement shop;

    @FindBy(xpath = "//a[normalize-space()='Find More']")
    WebElement find;

    public void startApplication()
    {
        driver.get("https://askomdch.com/");
    }

    public StorePage enterStore()
    {
        shop.click();
        StorePage storePage=new StorePage(driver);
        return storePage;
    }
}
