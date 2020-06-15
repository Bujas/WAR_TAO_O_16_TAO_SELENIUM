package pageObjectPattern.steps;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjectPattern.pages.HeaderComponent;
import pageObjectPattern.pages.LoginPage;
import pageObjectPattern.pages.MyAccountPage;
import pageObjectPattern.pages.UserInfoPage;

import java.util.concurrent.TimeUnit;

public class ChangeUserInfoSteps {
    WebDriver driver;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    HeaderComponent headerComponent;
    UserInfoPage userInfoPage;

    @Given("^User is logged in to CodersLab shop$")
    public void userIsLooggedInToCodersLabShop() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
        loginPage = new LoginPage(driver);
        headerComponent = new HeaderComponent(driver);
        myAccountPage = new MyAccountPage(driver);
        userInfoPage = new UserInfoPage(driver);
        loginPage.loginAs("michal.dobrzycki@coderslab.pl", "CodersLab");
    }

    @And("^User changes his birthday to \"([^\"]*)\"$")
    public void userChangesHisBirthdayTo(String date) {
        userInfoPage.setBirthdate(date);
    }

    @And("^User signs up for our newsletter$")
    public void userSignsUpForOurNewsletter() {
        userInfoPage.signInForNewsletter();
    }

    @And("^User saves information$")
    public void userSavesInformation() {
        userInfoPage.submitUserInfo();
    }

    @Then("^User sees \"([^\"]*)\"$")
    public void userSees(String message) {
        Assert.assertEquals(message, userInfoPage.getUpdateInformation());
    }


    @When("User goes to UserInformationPage")
    public void userGoesToUserInformationPage() {
        headerComponent.goToUserInformationView();
        myAccountPage.goToInformationPage();
    }
    @And("User close window")
    public void userCloseWindow() {
        driver.close();
    }
}
