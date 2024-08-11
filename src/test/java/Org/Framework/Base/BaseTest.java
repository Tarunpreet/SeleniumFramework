package Org.Framework.Base;

import EcommerceWeb.Pages.LandingPage;
import EcommerceWeb.Pages.StorePage;
import Org.Framework.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import Org.Framework.DataProvider.Products;

public class BaseTest extends Products {
    protected WebDriver driver;

    @BeforeMethod
    public void BaseTestInit()
    {
        driver = new DriverManager().initDriver();
    }
    @AfterMethod
    public void BaseTestExit()
    {
        driver.quit();
    }
    public LandingPage startApplication()
    {
        driver.get("https://askomdch.com/");
        LandingPage landingPage=new LandingPage(driver);
        return landingPage;
    }
}
