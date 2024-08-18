package EcommerceWeb.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StorePage extends BasePage {
    WebDriver driver;

    @FindBy(xpath = "//h1[@class='woocommerce-products-header__title page-title']")
    WebElement searchTitle;

    @FindBy(xpath="//input[@id='woocommerce-product-search-field-0']")
    WebElement searchFeild;

    @FindBy(xpath="//button[@value='Search']")
    WebElement searchButton;

    @FindBy(xpath="//a[@class='added_to_cart wc-forward']")
    WebElement viewCartButton;

    @FindBy(xpath="//h2[@class='woocommerce-loop-product__title']")
    List<WebElement> productNamesEleList;

    @FindBy(xpath="//a[@class='next page-numbers']")
    WebElement nextPageButton;

    @FindBy(css = ".orderby")
    WebElement filterButton;

    List<WebElement> cartElementList=new ArrayList<>();

    List<Integer> quantityListInOrder=new ArrayList<>();

    public  StorePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void searchProduct(String searchQuery)
    {
        searchFeild.sendKeys(searchQuery);
        searchButton.click();
    }
    
    public void addProductsToCart(List<String> productNameList,List<Integer> quantityList)
    {
        int totalElementClicked=0;

        do {
            cartElementList=productNamesEleList.stream().
                    filter(product -> productNameList.contains(product.getText())).
                    map(product -> giveAddToCartEle(product)).
                    collect(toList());

            productNamesEleList.stream().
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
                    nextPageButton.click();
                }
                catch (Exception E)
                {
                    break;
                }
            }

        }while(cartElementList.isEmpty()||totalElementClicked!= productNameList.size());
    }


    public WebElement giveAddToCartEle(WebElement product)
    {
        return product.findElement(By.xpath("parent::a/following-sibling::a"));
    }
    public int giveQuantityOfProduct(WebElement product,List<String> productNameList,List<Integer> quantityList)
    {
        int indexOfProduct=productNameList.indexOf(product.getText());
        return quantityList.get(indexOfProduct);
    }
    public List<Integer> giveQuantityInOrder()
    {
        return quantityListInOrder;
    }


    public CartPage viewCart()
    {
        viewCartButton.click();
        CartPage cartPage =new CartPage(driver);
        return cartPage;

    }
    public String getSearchTitle()
    {
        return searchTitle.getText();
    }
    public void load()
    {
        super.load("store/");
    }
    public void load(String searchItem)
    {
        super.load("?s="+searchItem+"&post_type=product");
    }

    public void selectFilterOption(String option)
    {
        filterButton.click();
        selectValue(filterButton,option);

    }
    //All prices enabled or disabled(Discount)
   By allPrices=By.xpath("//span[@class='price'] //span[@class='woocommerce-Price-amount amount']");

    public Boolean testPrices(Boolean ascending)
    {
        List<WebElement> allPricesElements=driver.findElements(allPrices);
        List<Double> prices=allPricesElements.stream().filter(ele->checkEnabled(ele)).map(ele->mapToPrice(ele)).toList();
        for(Double price:prices)
        {
            System.out.println(price);
        }
        List<Double> sortedPrices;
        if(ascending)
        {
            sortedPrices=prices.stream().sorted().toList();
            return prices.equals(sortedPrices);
        }
       sortedPrices=prices.stream().sorted(Collections.reverseOrder()).toList();
        return prices.equals(sortedPrices);

    }
    public Boolean checkEnabled(WebElement priceEle)
    {
        try {
            WebElement temp = priceEle.findElement(By.xpath("parent::del"));

        }
       catch (Exception e)
       {
           return true;
       }
        return false;
    }
    public Double mapToPrice(WebElement priceEle)
    {
        WebElement temp=priceEle.findElement(By.xpath("bdi"));
        String price[]=temp.getText().split("\\$");
        return Double.parseDouble(price[1]);
    }




}
