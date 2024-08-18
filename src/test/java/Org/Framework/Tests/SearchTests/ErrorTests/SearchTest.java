package Org.Framework.Tests.SearchTests.ErrorTests;

import EcommerceWeb.Pages.StorePage;
import Org.Framework.Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchTest extends BaseTest {
    @Test
    public void searchByText()
    {
        StorePage storePage=new StorePage(driver);
        storePage.load();
        storePage.searchProduct("Blue");
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleContains("Blue"));
        String actual=storePage.getSearchTitle();
        actual = actual.replace("“", "\"").replace("”", "\"");
        Assert.assertEquals(actual,"Search results: "+"\"Blue\"");
    }
}
