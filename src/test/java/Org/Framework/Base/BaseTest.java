package Org.Framework.Base;

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
        driver.get("https://askomdch.com/");
    }
    @AfterMethod
    public void BaseTestExit()
    {
        driver.quit();
    }
}
