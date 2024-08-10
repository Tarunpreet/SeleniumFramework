package Org.Framework.Tests;

import EcommerceWeb.Pages.*;
import Org.Framework.Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PlacingOrders extends BaseTest {



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





    @Test(dataProvider = "productDP")
    public void guestOrderPlacement(List<String> productNameList,List<Integer> quantityList) throws InterruptedException {

        LandingPage landingPage=new LandingPage(driver);
        landingPage.enterStore();

        StorePage storePage=new StorePage(driver);
        storePage.searchProduct("b");
        storePage.addProductsToCart(productNameList,quantityList);
        storePage.viewCart();

        CartPage cartPage =new CartPage(driver);
        Assert.assertTrue(cartPage.verifyProductNames(productNameList),"Product Names in cart doesn't match with Original Names List");
        List<Integer> quantity=storePage.giveQuantityInOrder();
        Assert.assertTrue(cartPage.verifyProductQuantity(quantity),"Cart Products Quantity doesnt Match the Orignal Products Quantity");
        cartPage.goToCheckout();

        HashMap<String,String> billingDetails=getBillingData();
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        checkoutPage.fillDetails(billingDetails);
        checkoutPage.placeOrder();

        ConfirmPage confirmPage=new ConfirmPage(driver);
        Assert.assertTrue(confirmPage.verifyMessage(),"Message Doesnt Match");

    }

}
