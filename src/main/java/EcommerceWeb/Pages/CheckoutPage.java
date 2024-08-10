package EcommerceWeb.Pages;

import EcommerceWeb.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.HashMap;

public class CheckoutPage extends BasePage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="#billing_first_name")
    WebElement firstNameInput;
    @FindBy(css="#billing_last_name")
    WebElement lastNameInput;
    @FindBy(css="#billing_address_1")
    WebElement addressInput;
    @FindBy(css="#billing_city")
    WebElement cityInput;
    @FindBy(css="#billing_postcode")
    WebElement postCodeInput;
    @FindBy(css="#billing_email")
    WebElement emailInput;
    @FindBy(xpath = "//button[@name='woocommerce_checkout_place_order']")
    WebElement placeOrderBtn;

    public void fillDetails(HashMap<String,String> billingDetails)
    {
        firstNameInput.sendKeys(billingDetails.get("First Name"));
        lastNameInput.sendKeys(billingDetails.get("Last Name"));
        addressInput.sendKeys(billingDetails.get("Address"));
        cityInput.sendKeys(billingDetails.get("City"));
        postCodeInput.sendKeys(billingDetails.get("Pincode"));
        emailInput.sendKeys(billingDetails.get("Email"));
        //        Actions action=new Actions(driver);
//        action.click(driver.findElement(By.xpath("//span[@id='select2-billing_country-container']"))).
//               click(driver.findElement(By.xpath("//input[@class='select2-search__field']"))).
//                sendKeys(billingDetails.get("Country")).build().perform();
//
//        countriesList=driver.findElements(By.cssSelector(".select2-results__option"));
//        countriesList.stream().
//                filter(country->country.getText().equalsIgnoreCase(billingDetails.get("Country"))).
//                limit(1).
//                forEach(countryEle-> countryEle.click());

 //       Actions action1=new Actions(driver);
//        action1.click(driver.findElement(By.xpath("//span[@class='select2 select2-container select2-container--default select2-container--below select2-container--focus']"))).
//                click(driver.findElement(By.cssSelector(".select2-search__field"))).
//                sendKeys(billingDetails.get("City")).build().perform();
//
//        stateList=driver.findElements(By.cssSelector(".select2-results__option"));
//        stateList.stream().
//                filter(state->state.getText().equalsIgnoreCase(billingDetails.get("City"))).
//                limit(1).
//                forEach(stateEle-> stateEle.click());

    }
    public void placeOrder() throws InterruptedException {
        placeOrderBtn.click();
        Thread.sleep(Duration.ofSeconds(2));
    }

}
