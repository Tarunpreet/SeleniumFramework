package Org.Framework.Tests;

import EcommerceWeb.Objects.BillingInfo;
import EcommerceWeb.Pages.*;
import Org.Framework.Base.BaseTest;
import Org.Framework.Utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class PlacingOrdersTest extends BaseTest {



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
    public void guestOrderPlacementTest(List<String> productNameList, List<Integer> quantityList) throws InterruptedException {

        LandingPage landingPage=new LandingPage(driver);
        landingPage.startApplication();
        StorePage storePage=landingPage.enterStore();

        storePage.searchProduct("b");
        storePage.addProductsToCart(productNameList,quantityList);
        CartPage cartPage =storePage.viewCart();

        Assert.assertTrue(cartPage.verifyProductNames(productNameList),"Product Names in cart doesn't match with Original Names List");
        List<Integer> quantity=storePage.giveQuantityInOrder();
        Assert.assertTrue(cartPage.verifyProductQuantity(quantity),"Cart Products Quantity doesnt Match the Orignal Products Quantity");
        CheckoutPage checkoutPage=cartPage.goToCheckout();


        //HashMap<String,String> billingDetails=getBillingData();
        BillingInfo billingDetails= JsonUtil.deserializationToClass("BillingData",BillingInfo.class);

        checkoutPage.fillDetails(billingDetails);
        ConfirmPage confirmPage=checkoutPage.placeOrder();
        Assert.assertTrue(confirmPage.verifyMessage(),"Message Doesnt Match");


    }


}
