package EcommerceWeb.Pages;

import EcommerceWeb.DriverUtil.PageUtils;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage extends PageUtils {
    //Generic function-> Wait,switches,alert
    //only initalized using PageClasses(childs), So using super(s) in them as this class also needs driver
    WebDriver driver;
    public BasePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }


}
