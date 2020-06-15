package pageObjectPattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Instant;

public class NewAddressPage {
    private static WebDriver driver;
    @FindBy(name = "alias")
    WebElement aliasInput;
    @FindBy(name = "company")
    WebElement companyInput;
    @FindBy(name = "vat_number")
    WebElement vatNumberInput;
    @FindBy(name = "address1")
    WebElement addressInput;
    @FindBy(name = "address2")
    WebElement addressComplementInput;
    @FindBy(name = "city")
    WebElement cityInput;
    @FindBy(name = "postcode")
    WebElement postcodeInput;
    @FindBy(name = "phone")
    WebElement phoneInput;
    @FindBy(xpath = "//*[@id='content']/div/div/form/footer/button")
    WebElement saveButton;

    public NewAddressPage(WebDriver driver) {
        NewAddressPage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillUpAddress(String address) {
        addressInput.sendKeys(address);
    }
    public void fillUpAddressComplement(String addressComplement) {
        addressComplementInput.sendKeys(addressComplement);
    }

    public void fillUpAlias() {
        aliasInput.sendKeys(getRandomAlias());
    }

    public void fillUpCompany(String company) {
        companyInput.sendKeys(company);
    }

    public void fillUpVatNumber(String vatNumber) {
        vatNumberInput.sendKeys(vatNumber);
    }
    public void fillUpPhoneNumber(String phoneNumber) {
        phoneInput.sendKeys(phoneNumber);
    }

    public void fillUpCity(String city) {
        cityInput.sendKeys(city);
    }

    public void fillUpPostalCode(String postCode) {
        postcodeInput.sendKeys(postCode);
    }

    public void clickOnSaveButton() {
        saveButton.click();
    }

    private String getRandomAlias(){
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli() / 1000;
        return "Alias " + timeStampMillis;
    }
}
