package EcommerceWeb;

import DataProvider.Products;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PlacingOrders extends Products {

    public WebElement giveAddToCartEle(WebElement product)
    {
        return product.findElement(By.xpath("parent::a/following-sibling::a"));
    }
    public int giveQuantityOfProduct(WebElement product,List<String> productNameList,List<Integer> quantityList)
    {
        int indexOfProduct=productNameList.indexOf(product.getText());
        return quantityList.get(indexOfProduct);
    }
    public void addToCartProdcutsAndValidate(String searchQuery,List<String> productNameList,List<Integer> quantityList,WebDriver driver)
    {
        driver.findElement(By.xpath("//input[@id='woocommerce-product-search-field-0']"))
                .sendKeys(searchQuery);
        driver.findElement(By.xpath("//button[@value='Search']")).click();

        List<WebElement> cartElementList=new ArrayList<>();
        List<Integer> quantityListInOrder=new ArrayList<>();
        int totalElementClicked=0;

        do {
               List<WebElement> productNames=driver.findElements(By.xpath("//h2[@class='woocommerce-loop-product__title']"));

               cartElementList=productNames.stream().
               filter(product -> productNameList.contains(product.getText())).
               map(product -> giveAddToCartEle(product)).
               collect(Collectors.toList());

               productNames.stream().
               filter(product -> productNameList.contains(product.getText())).
               map(product -> giveQuantityOfProduct(product,productNameList,quantityList)).
               forEach(quantity->quantityListInOrder.add(quantity));

               if(cartElementList!=null)
               {
                for(int i=0;i<cartElementList.size();i++)
                 {
                     totalElementClicked++;
                    int quantity= quantityListInOrder.get(i);
                    for(int j=0;j<quantity;j++)
                    {
                        cartElementList.get(i).click();
                    }
                 }
               }


            if(cartElementList.isEmpty()||totalElementClicked!= productNameList.size())
            {
                try{
                    driver.findElement(By.xpath("//a[@class='next page-numbers']")).click();
                }
                catch (Exception E)
                {
                    break;
                }
            }

        }while(cartElementList.isEmpty()||totalElementClicked!= productNameList.size());
        List<WebElement>  cartProductNamesElem=new ArrayList<>();
        List<WebElement>  cartProductQuantityElem=new ArrayList<>();
        List <Integer>     cartProductQuantity=new ArrayList<>();

        driver.findElement(By.xpath("//a[@class='added_to_cart wc-forward']")).click();

        cartProductNamesElem=driver.findElements(By.xpath( "//td[@class='product-name']/a"));
        cartProductNamesElem.stream().
                             forEach(product-> Assert.assertTrue(productNameList.contains(product.getText()),"Cart Products Name is not in Orignal Product Names"));

        cartProductQuantityElem=driver.findElements(By.xpath( "//input[@class='input-text qty text']"));
        cartProductQuantity=cartProductQuantityElem.stream().
                            map(product->Integer.parseInt(product.getAttribute("value"))).
                            collect(Collectors.toList());


        Assert.assertTrue(cartProductQuantity.equals(quantityListInOrder),"Cart Products Quantity doesnt Match the Orignal Products Quantity");

        driver.findElement(By.cssSelector(".checkout-button")).click();

    }
    public HashMap<String,String> getBillingData()
    {
        HashMap<String,String> billingData=new HashMap<>();
        billingData.put("First Name","Tarunpreet");
        billingData.put("Last Name","Singh");
        billingData.put("Country","India");
        billingData.put("Address","Meera Bagh,New Delhi");
        billingData.put("City","Delhi");
        billingData.put("Pincode","94188");
        billingData.put("Email","taru@gmail.com");
        return billingData;
    }

    public void addCheckoutDetailsAndVaildate(HashMap<String,String> billingDetails,WebDriver driver) throws InterruptedException {
        List<WebElement> countriesList=new ArrayList<>();
        List<WebElement> stateList=new ArrayList<>();

        driver.findElement(By.cssSelector("#billing_first_name")).sendKeys(billingDetails.get("First Name"));
        driver.findElement(By.cssSelector("#billing_last_name")).sendKeys(billingDetails.get("Last Name"));

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

        driver.findElement(By.cssSelector("#billing_address_1")).sendKeys(billingDetails.get("Address"));
        driver.findElement(By.cssSelector("#billing_city")).sendKeys(billingDetails.get("City"));
//
//        Actions action1=new Actions(driver);
//        action1.click(driver.findElement(By.xpath("//span[@class='select2 select2-container select2-container--default select2-container--below select2-container--focus']"))).
//                click(driver.findElement(By.cssSelector(".select2-search__field"))).
//                sendKeys(billingDetails.get("City")).build().perform();
//
//        stateList=driver.findElements(By.cssSelector(".select2-results__option"));
//        stateList.stream().
//                filter(state->state.getText().equalsIgnoreCase(billingDetails.get("City"))).
//                limit(1).
//                forEach(stateEle-> stateEle.click());

        driver.findElement(By.cssSelector("#billing_postcode")).sendKeys(billingDetails.get("Pincode"));
        driver.findElement(By.cssSelector("#billing_email")).sendKeys(billingDetails.get("Email"));
        driver.findElement(By.xpath("//button[@name='woocommerce_checkout_place_order']")).click();
        Thread.sleep(Duration.ofSeconds(2));
    }
    public void vaildateOrderPlacement(WebDriver driver)
    {
        String message=driver.findElement(By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")).getText();
        Assert.assertEquals(message,"Thank you. Your order has been received.");
    }


    @Test(dataProvider = "productDP")
    public void guestOrderPlacement(List<String> productNameList,List<Integer> quantityList) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//div[@class='wp-block-button']")).click();
        addToCartProdcutsAndValidate("b",productNameList,quantityList,driver);
        HashMap<String,String> billingDetails=getBillingData();
        addCheckoutDetailsAndVaildate(billingDetails,driver);
        vaildateOrderPlacement(driver);

    }

}
