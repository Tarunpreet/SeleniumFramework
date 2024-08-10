package EcommerceWeb.Pages;

import EcommerceWeb.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorePage extends BasePage {
    WebDriver driver;

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
                    collect(Collectors.toList());

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


    public void viewCart()
    {
        viewCartButton.click();
    }



}
