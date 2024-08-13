package Org.Framework.factory;

import Org.Framework.Utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {

    public WebDriver initDriver()
    {
        String browser=ConfigManager.getInstance().getStringProperty("browser");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return driver;
    }
}
