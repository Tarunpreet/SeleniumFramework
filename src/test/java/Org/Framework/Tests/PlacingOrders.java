package Org.Framework.Tests;

import EcommerceWeb.Objects.BillingInfo;
import EcommerceWeb.Pages.*;
import Org.Framework.Base.BaseTest;
import Org.Framework.Utils.JsonUtil;
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
        billingData.put("Pincode","110087");
        billingData.put("Email","taru@gmail.com");
        return billingData;
    }





    @Test(dataProvider = "productDP")
    public void guestOrderPlacement(List<String> productNameList,List<Integer> quantityList) throws InterruptedException {

        LandingPage landingPage=startApplication();
        StorePage storePage=landingPage.enterStore();

        storePage.searchProduct("b");
        storePage.addProductsToCart(productNameList,quantityList);
        CartPage cartPage =storePage.viewCart();

        Assert.assertTrue(cartPage.verifyProductNames(productNameList),"Product Names in cart doesn't match with Original Names List");
        List<Integer> quantity=storePage.giveQuantityInOrder();
        Assert.assertTrue(cartPage.verifyProductQuantity(quantity),"Cart Products Quantity doesnt Match the Orignal Products Quantity");
        CheckoutPage checkoutPage=cartPage.goToCheckout();


        //HashMap<String,String> billingDetails=getBillingData();
        BillingInfo billingDetails= JsonUtil.deserialization("BillingData",BillingInfo.class);

        checkoutPage.fillDetails(billingDetails);
        ConfirmPage confirmPage=checkoutPage.placeOrder();
        Assert.assertTrue(confirmPage.verifyMessage(),"Message Doesnt Match");


    }

}
