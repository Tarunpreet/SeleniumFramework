package Org.Framework.factory;

import Org.Framework.Utils.ConfigManager;
import lombok.Synchronized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {
    WebDriver driver;
    public static ThreadLocal<WebDriver> td=new ThreadLocal<>();

    public WebDriver initDriver()
    {
        String browser=ConfigManager.getInstance().getStringProperty("browser");
        driver=setDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return driver;
    }
    private synchronized WebDriver setDriver() {
        driver = new ChromeDriver();
        td.set(driver);
        return getDriver();
    }
    public synchronized WebDriver getDriver()
    {
        return td.get();
    }
}
