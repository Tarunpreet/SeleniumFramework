package Org.Framework.Tests.NavigationTests;

import EcommerceWeb.Pages.LandingPage;
import EcommerceWeb.Pages.StorePage;
import Org.Framework.Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigateFromHeader extends BaseTest {

    @Test
    public void navigateToStore()
    {
        LandingPage landingPage=new LandingPage(driver);
        landingPage.startApplication();
        StorePage storePage=landingPage.enterStoreFromHeader();
        Assert.assertEquals(storePage.getSearchTitle(),"Store");

    }
    @Test
    public void navigateToMenStore()
    {
        LandingPage landingPage=new LandingPage(driver);
        landingPage.startApplication();
        StorePage storePage=landingPage.enterMenStoreFromHeader();
        Assert.assertEquals(storePage.getSearchTitle(),"Men");

    }
    @Test
    public void navigateToWomenStore()
    {
        LandingPage landingPage=new LandingPage(driver);
        landingPage.startApplication();
        StorePage storePage=landingPage.enterWomenStoreFromHeader();
        Assert.assertEquals(storePage.getSearchTitle(),"Women");

    }
    @Test
    public void navigateToAcessStore()
    {
        LandingPage landingPage=new LandingPage(driver);
        landingPage.startApplication();
        StorePage storePage=landingPage.enterAccessoriesFromHeader();
        Assert.assertEquals(storePage.getSearchTitle(),"Accessories");

    }

}
