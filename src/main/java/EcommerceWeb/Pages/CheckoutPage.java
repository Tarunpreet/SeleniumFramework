package EcommerceWeb.Pages;

import EcommerceWeb.BasePage.BasePage;
import EcommerceWeb.Objects.BillingInfo;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

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
    @FindBy(xpath = "//span[@id='select2-billing_country-container']")
    WebElement countryInput;
    @FindBy(xpath = "//span[@id='select2-billing_state-container']")
    WebElement stateInput;

    //Before submit button
    By overlay= By.xpath("//div[@class='blockUI blockOverlay']");


    List<WebElement> countriesList;
    List<WebElement> stateList;

    public void fillDetails(BillingInfo billingDetails)
    {
            firstNameInput.sendKeys(billingDetails.getFirstName());
            lastNameInput.sendKeys(billingDetails.getLastName());
            addressInput.sendKeys(billingDetails.getAddress());
            cityInput.sendKeys(billingDetails.getCity());
            postCodeInput.sendKeys(billingDetails.getPostCode());
            emailInput.sendKeys(billingDetails.getEmail());
            Actions action=new Actions(driver);
            action.click(countryInput).build().perform();

           waitForElementToBeLocated(By.cssSelector(".select2-search__field"));

            action.click(driver.findElement(By.xpath("//input[@class='select2-search__field']"))).
                    sendKeys(billingDetails.getCountry()).build().perform();

            countriesList=driver.findElements(By.cssSelector(".select2-results__option"));
            countriesList.stream().
                    filter(country->country.getText().equalsIgnoreCase(billingDetails.getCountry())).
                    limit(1).
                    forEach(countryEle-> countryEle.click());

            Actions action1=new Actions(driver);
            action1.click(stateInput).build().perform();

            waitForElementToBeLocated(By.cssSelector(".select2-search__field"));

            action1.click(driver.findElement(By.cssSelector(".select2-search__field"))).
                    sendKeys(billingDetails.getState()).build().perform();

            stateList=driver.findElements(By.cssSelector(".select2-results__option"));
            stateList.stream().
                    filter(state->state.getText().equalsIgnoreCase(billingDetails.getState())).
                    limit(1).
                    forEach(stateEle-> stateEle.click());

    }
    public ConfirmPage placeOrder() throws InterruptedException {
       waitForElementsListToBeInvisible(overlay);
        placeOrderBtn.click();
        ConfirmPage confirmPage=new ConfirmPage(driver);
        return confirmPage;
    }

}
