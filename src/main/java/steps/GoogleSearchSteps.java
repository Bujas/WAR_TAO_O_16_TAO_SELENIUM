package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchSteps {

  WebDriver driver;

  @Given("I am on the Google search page")
  public void iAmOnGoogleSearchPage() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("http://google.com");
  }

  @When("^a keyword \"(.*)\" is entered in input field$")
  public void typeGoogleSearchText(String searchText) {
    WebElement googleSearchInput = driver.findElement(By.name("q"));
    googleSearchInput.clear();
    googleSearchInput.sendKeys(searchText);
    googleSearchInput.submit();
  }

  @Then("^the first one should contain \"(.*)\"$")
  public void clickSearchButton(String expectedKeyword) {
    WebElement firstResult = driver.findElements(By.xpath("//a/h3")).get(0);
    assert firstResult.getText().contains(expectedKeyword);

//    new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
//      public Boolean apply(WebDriver d) {
//        return d.getTitle().toLowerCase().startsWith(expectedKeyword);
//      }
//    });
//
//    assert driver.getTitle().equals(expectedKeyword);
  }

  @And("^close browser$")
  public void closeBrowser() {
    driver.quit();
  }
}
