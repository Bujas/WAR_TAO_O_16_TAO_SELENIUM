package przyklad1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class SearchShop {
    private WebDriver driver;
    String[] products = {"Hummingbird Printed T-Shirt", "Hummingbird Printed Sweater", "The Best Is Yet To Come", "The Adventure Begins Framed", "Mug The Best Is Yet To Come"};

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        // Uruchom nowy egzemplarz przeglądarki Chrome
        driver = new ChromeDriver();
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do Google
        driver.get("https://prod-kurs.coderslab.pl/index.php");
    }

    @Test
    public void searchProductInShop() {
        WebElement element = driver.findElement(By.name("s"));
        element.sendKeys(RandomizeItem.getRandomElementFromTable(products));
        element.submit();
    }

    @After
    public void tearDown(){
        // Zamknij przeglądarkę
        driver.quit();
    }

    private String returnProduct(){
        Random random = new Random();
        int randomInteger = random.nextInt(50);
        int productIndex = randomInteger % products.length;
        return products[productIndex];
    }

}
