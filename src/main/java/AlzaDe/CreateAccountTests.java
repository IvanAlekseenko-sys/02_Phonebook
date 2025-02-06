package AlzaDe;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateAccountTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=de-DE");
        driver = new ChromeDriver(options);
        driver.get("https://www.alza.de/Registration.htm");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void CreateAccountPositiveTests() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".js-cookies-info-accept")));
            acceptCookies.click();
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("нет куки попапа, тест продолжается");
        }
        driver.findElement(By.cssSelector("#edth1EmailLogin")).click();
        //driver.findElement(By.cssSelector("#edth1EmailLogin")).clear(); //не срабатывает
        driver.findElement(By.cssSelector("#edth1EmailLogin")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.cssSelector("#edth1EmailLogin")).sendKeys(Keys.DELETE);
        driver.findElement(By.cssSelector("#edth1EmailLogin")).sendKeys("pppaaa@ppp.com");
        driver.findElement(By.cssSelector("#edth1Password")).click();
        driver.findElement(By.cssSelector("#edth1Password")).sendKeys("Password@1");
        driver.findElement(By.cssSelector("#edth1PasswordConfirm")).sendKeys("Password@1");
        driver.findElement(By.xpath("//*[@id='ddlPhoneCountryPrefix-button']//span[3]")).click();
        driver.findElement(By.cssSelector("#ui-id-4")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'inpTelNumber')]")).click();
        driver.findElement(By.xpath("//*[contains(@id, 'inpTelNumber')]")).sendKeys("99999999");
        driver.findElement(By.cssSelector("#edth2Name")).click();
        driver.findElement(By.cssSelector("#edth2Name")).sendKeys("Ananas Ananasov");
        driver.findElement(By.cssSelector("#edth2Street")).click();
        driver.findElement(By.cssSelector("#edth2Street")).sendKeys("Ananasovaya str. 10");
        driver.findElement(By.cssSelector("#edth2City")).click();
        driver.findElement(By.cssSelector("#edth2City")).sendKeys("Fruit Paradise");
        driver.findElement(By.cssSelector("#edth2Zip")).click();
        driver.findElement(By.cssSelector("#edth2Zip")).sendKeys("22334");
        driver.findElement(By.xpath("//span[contains(text(),'Speichern')]")).click();
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }
}
