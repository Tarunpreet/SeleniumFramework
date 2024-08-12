package Org.Framework.Base;

import EcommerceWeb.Pages.LandingPage;
import EcommerceWeb.Pages.StorePage;
import Org.Framework.factory.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;
import Org.Framework.DataProvider.Products;

import java.io.File;
import java.io.IOException;

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
    public void takeScreenshot(String testcase)
    {
        TakesScreenshot screenshot=(TakesScreenshot)driver;
        File srcFile=screenshot.getScreenshotAs(OutputType.FILE);
        String path=System.getProperty("user.dir")+"/src/main/Reports/"+testcase+".png";
        File desFile=new File(path);
        try {
            FileHandler.copy(srcFile,desFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
