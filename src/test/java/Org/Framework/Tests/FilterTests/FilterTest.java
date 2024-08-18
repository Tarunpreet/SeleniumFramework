package Org.Framework.Tests.FilterTests;

import EcommerceWeb.Pages.StorePage;
import Org.Framework.Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterTest extends BaseTest {
    @Test
    public void filterByPriceAscending()
    {
        StorePage storePage=new StorePage(driver);
        storePage.load("Blue");
        storePage.selectFilterOption("price");
        Assert.assertTrue(storePage.testPrices(true));
    }
    @Test
    public void filterByPriceDescending()
    {
        StorePage storePage=new StorePage(driver);
        storePage.load("Jeans");
        storePage.selectFilterOption("price-desc");
        Assert.assertTrue(storePage.testPrices(false));
    }

}
