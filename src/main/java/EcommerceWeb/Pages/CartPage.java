package EcommerceWeb.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {
    WebDriver driver;

    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//td[@class='product-name']/a")
    List<WebElement>  cartProductNamesElem;

    @FindBy(xpath = "//input[@class='input-text qty text']")
    List<WebElement> cartProductQuantityElem;

    @FindBy(css=".checkout-button")
    WebElement checkoutBtn;

    public Boolean verifyProductNames(List<String> productNameList)
    {
         List<String> cartProductNameList=cartProductNamesElem.stream().
                map(product->product.getText()).toList();
         return cartProductNameList.equals(productNameList);
    }
    public Boolean verifyProductQuantity(List<Integer> quantityListInOrder)
    {
        List<Integer> cartProductQuantity=cartProductQuantityElem.stream().
                map(product->Integer.parseInt(product.getAttribute("value"))).
                toList();
        return cartProductQuantity.equals(quantityListInOrder);
    }
    public CheckoutPage goToCheckout()
    {
        checkoutBtn.click();
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        return checkoutPage;
    }


}
