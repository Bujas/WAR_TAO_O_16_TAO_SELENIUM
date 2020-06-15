package pageObjectPattern.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class AddressesPage {
    private static WebDriver driver;

    @FindBy(xpath = "//*[@data-link-action='add-address']")
    WebElement createNewAddressButton;

    @FindBy(xpath = "//*[@id='notifications']/div/article")
    WebElement successInformation;

    @FindBy(xpath = "//address")
    List<WebElement> addressList;

    public AddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createNewAddress() {
        createNewAddressButton.click();
    }

    public void isSuccessInformationIsDisplay() {
        Assert.assertTrue(successInformation.isDisplayed());
    }

    public void isAddressCorrect(String company, String vatNumber, String address, String addressComplement, String city, String postalCode, String phone) {
        WebElement lastAddedAddress = addressList.get(addressList.size() - 1);
        Assert.assertTrue(lastAddedAddress.getText().contains(company));
        Assert.assertTrue(lastAddedAddress.getText().contains(vatNumber));
        Assert.assertTrue(lastAddedAddress.getText().contains(address));
        Assert.assertTrue(lastAddedAddress.getText().contains(addressComplement));
        Assert.assertTrue(lastAddedAddress.getText().contains(city));
        Assert.assertTrue(lastAddedAddress.getText().contains(postalCode));
        Assert.assertTrue(lastAddedAddress.getText().contains(phone));
    }
}
