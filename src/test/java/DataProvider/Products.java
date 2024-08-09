package DataProvider;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Products {

    @DataProvider
    public Object[][] productDP()
    {
       List<String> productNamesList1=Arrays.asList("Blue Shoes", "Blue Denim Shorts", "Blue Tshirt");
       List<Integer> quantityList1=Arrays.asList(1, 2, 1);

//        List<String> productNamesList2= Arrays.asList("Blue Shoes", "Blue Denim Shorts");
//        List<Integer> quantityList2=Arrays.asList(2, 3);




        return new Object[][]{
                {productNamesList1,quantityList1}
//                ,{productNamesList2,quantityList2}
        };

    }
}
