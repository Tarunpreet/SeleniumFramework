package EcommerceWeb.Objects;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class BillingInfo {

    String firstName;

    String lastName;

    String address;

    String city;

    String state;

    String postCode;

    String email;

    String country;

}
