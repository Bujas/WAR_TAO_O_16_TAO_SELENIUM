package pageObjectPattern.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjectPattern.pages.*;

import java.util.concurrent.TimeUnit;


public class AddNewAddressTest {
    private final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private final String CHROME_DRIVER_PATH = "src/main/resources/drivers/chromedriver.exe";
    private final String URL = "https://prod-kurs.coderslab.pl/index.php?controller=authentication";
    private final String USER = "michal.dobrzycki@coderslab.pl";
    private final String PASSWORD = "CodersLab";
    private final String address = "random address";
    private final String addressComplement = "random address complement";
    private final String company = "random company";
    private final String vatNumber = "random vat number";
    private final String phoneNumber = "987654321";
    private final String city = "random city";
    private final String postalCode = "random code";
    MyAccountPage myAccountPage;
    HeaderComponent headerComponent;
    LoginPage loginPage;
    UserInfoPage userInfoPage;
    AddressesPage addressesPage;
    NewAddressPage newAddressPage;
    WebDriver driver;

    @Before
    public void prepareEnvironment() {
        System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
        loginPage = new LoginPage(driver);
        userInfoPage = new UserInfoPage(driver);
        myAccountPage = new MyAccountPage(driver);
        headerComponent = new HeaderComponent(driver);
        addressesPage = new AddressesPage(driver);
        newAddressPage = new NewAddressPage(driver);
        loginPage.loginAs(USER, PASSWORD);
    }

    @Test
    public void testAddNewUserAddress() {
        headerComponent.goToUserInformationView();
        myAccountPage.goToAddressesPage();
        addressesPage.createNewAddress();
        newAddressPage.fillUpAlias();
        newAddressPage.fillUpCompany(company);
        newAddressPage.fillUpVatNumber(vatNumber);
        newAddressPage.fillUpAddress(address);
        newAddressPage.fillUpAddressComplement(addressComplement);
        newAddressPage.fillUpCity(city);
        newAddressPage.fillUpPostalCode(postalCode);
        newAddressPage.fillUpPhoneNumber(phoneNumber);
        newAddressPage.clickOnSaveButton();
        addressesPage.isAddressCorrect(company,vatNumber,address,addressComplement,city,postalCode,phoneNumber);
        addressesPage.isSuccessInformationIsDisplay();
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

}
