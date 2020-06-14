package steps;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CodersLabSteps {

  WebDriver driver;

  @Given("I am on the Coders Lab page")
  public void iAmOnGoogleSearchPage() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("https://qloapps.coderslab.pl");
  }

  @When("^I click sign in$")
  public void clickSignIn() {
    WebElement signInButton = driver.findElement(By.xpath("//span[text()='Sign in']"));
    signInButton.click();
  }

  @When("^I enter following email address \"(.*)\" and submit$")
  public void enterEmailAddress(String emailAddress) {
    WebElement emailAddressInput = driver.findElement(By.name("email_create"));
    emailAddressInput.sendKeys(emailAddress);

    WebElement createAccountButton = driver.findElement(By.id("SubmitCreate"));
    createAccountButton.click();
  }

  @When("^I choose \"(Mr.|Mrs.)\" title$")
  public void chooseTitle(String title) {
    WebElement genderCheckBox;
    if (title.equals("Mr.")) {
      genderCheckBox = driver.findElement(By.id("id_gender1"));
    } else {
      genderCheckBox = driver.findElement(By.id("id_gender2"));
    }
    genderCheckBox.click();
  }

  @When("^I type first name \"(.*)\" and last name\"(.*)\"$")
  public void typeFullName(String firstName, String lastName) {
    WebElement firstNameInput = driver.findElement(By.id("customer_firstname"));
    firstNameInput.sendKeys(firstName);

    WebElement lastNameInput = driver.findElement(By.id("customer_lastname"));
    lastNameInput.sendKeys(lastName);
  }

  @When("^I confirm email address$")
  public void confirmPassword() {
    WebElement emailAddressInput = driver.findElement(By.id("email"));
    emailAddressInput.click();
  }

  @When("^I type password \"(.*)\"$")
  public void typePassword(String password) {
    WebElement passwordInput = driver.findElement(By.id("passwd"));
    passwordInput.sendKeys(password);
  }

  @When("^I select date of birth \"(.*)\"$")
  public void selectDateOfBirth(String date) {
    String[] dateOfBirth = date.split("-");
    Select dayOfBirthSelect = new Select(driver.findElement(By.id("days")));
    dayOfBirthSelect.selectByValue(dateOfBirth[0]);

    Select monthOfBirthSelect = new Select(driver.findElement(By.id("months")));
    monthOfBirthSelect.selectByValue(dateOfBirth[1]);

    Select yearOfBirthSelect = new Select(driver.findElement(By.id("years")));
    yearOfBirthSelect.selectByValue(dateOfBirth[2]);
  }

  @When("^I check newsletter checkbox$")
  public void checkNewsletterCheckbox() {
    WebElement newsLetterCheckbox = driver.findElement(By.cssSelector("#uniform-newsletter span"));

    if (!newsLetterCheckbox.getAttribute("class").equalsIgnoreCase("checked")) {
      newsLetterCheckbox.click();
    }
  }

  @When("^I check special offers checkbox$")
  public void checkSpecialOffersCheckbox() {
    WebElement specialOffersCheckbox = driver.findElement(By.id("uniform-optin"));

    //System.out.println("is selected1: " + specialOffersCheckbox.isSelected());
    if (!specialOffersCheckbox.isSelected()) {
      specialOffersCheckbox.click();
    }
    //System.out.println("is selected2: " + specialOffersCheckbox.isSelected());
  }

  @When("^I fill in personal information$")
  public void fillInPersonalInformation(Map<String, String> inboxParams) {
    chooseTitle(inboxParams.get("Title"));
    typeFullName(inboxParams.get("FirstName"), inboxParams.get("LastName"));
    confirmPassword();
    typePassword(inboxParams.get("Password"));
    selectDateOfBirth(inboxParams.get("DateOfBirth"));
    checkNewsletterCheckbox();
    checkSpecialOffersCheckbox();
  }

  @When("^I register new user$")
  public void registerNewUser() {
    WebElement specialOffersCheckbox = driver.findElement(By.id("submitAccount"));
    specialOffersCheckbox.click();
  }

  @When("^Account was created and I can see following information \"(.*)\"$")
  public void accountWasCreated(String expectedTxt) {
    WebElement specialOffersCheckbox = driver
        .findElement(By.xpath("//p[contains(@class,'alert-success')]"));
    assert specialOffersCheckbox.getText().equals(expectedTxt);
  }

  @And("^quit browser$")
  public void quitBrowser() {
    driver.quit();
  }

  @When("^I login Coders Lab account with email address \"(.*)\" and password \"(.*)\"$")
  public void loginCodersLabAccount(String emailAddress, String password) {
    WebElement emailInput = driver.findElement(By.id("email"));
    emailInput.sendKeys(emailAddress);

    WebElement passwd = driver.findElement(By.id("passwd"));
    passwd.sendKeys(password);

    WebElement signInButton = driver.findElement(By.id("SubmitLogin"));
    signInButton.click();
  }

  @Then("^Home page was loaded for user \"(.*)\"$")
  public void homePageWasLoaded(String firstName) {
    WebElement userInfo = driver.findElement(By.id("user_info_acc"));
    assert (userInfo.getText()).equals(firstName);
    assert (driver.getTitle()).equals("My account - myHotel");
    assert (driver.getCurrentUrl()).endsWith("/my-account");
  }

  @Then("^I am on login page$")
  public void iAmOnLoginPage() {
    driver.findElement(By.id("email"));
    driver.findElement(By.id("passwd"));
    driver.findElement(By.id("SubmitLogin"));

    assert (driver.getTitle()).contains("Login - myHotel");
    assert (driver.getCurrentUrl()).endsWith("/login");
  }

  @Then("^Following error message was displayed \"(.*)\"$")
  public void errorWasDisplayed(String expectedErrorMessage) {
    String obtainedErrorMessage = driver.findElement(By.cssSelector(".alert-danger")).getText();

    assert (obtainedErrorMessage).contains(expectedErrorMessage);
  }
}
