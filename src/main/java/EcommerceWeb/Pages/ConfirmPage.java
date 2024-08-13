package EcommerceWeb.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmPage extends BasePage {
    WebDriver driver;

    public ConfirmPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")
    WebElement messageEle;

    public Boolean verifyMessage()
    {
        String message=messageEle.getText();
        return message.equals("Thank you. Your order has been received.");
    }

}
