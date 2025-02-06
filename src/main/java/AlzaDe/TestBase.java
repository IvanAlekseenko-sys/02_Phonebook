package AlzaDe;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=de-DE");
        driver = new ChromeDriver(options);
        driver.get("https://identity.alza.cz/Account/Login?ReturnUrl=%2Fconnect%2Fauthorize%2Fcallback%3Facr_values%3Dcountry%253ADE%2520regLink%253AProduction_registration%2520source%253AWeb%26client_id%3Dalza%26culture%3Dde-DE%26nonce%3Drandom_nonce%26redirect_uri%3Dhttps%253A%252F%252Fwww.alza.de%252Fexternal%252Fcallback%253Fnav%253DLz9sb2dvdXRTdWNjZXNzPTE%26response_mode%3Dform_post%26response_type%3Dcode%2520id_token%26scope%3Demail%2520openid%2520profile%2520alza%2520offline_access%26state%3Dba7eef96-a090-44de-8851-0283f7c04191");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }

    public boolean isHomeComponentPresent() {
        return isElementPresent(By.cssSelector("div:nth-child(2) h1"));
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert().accept();
            return true;
        }
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void typePassword(String password) {
        type(By.name("password"), password);
    }

    public void typeEmail(String email) {
        type(By.name("email"), email);
    }

    public void clickOnLoginButton() {
        click(By.name("login"));
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[.='LOGIN']"));
    }

    public void checkLogin(By locator) {
        Assert.assertTrue(isElementPresent(locator));
    }

    public void login(String email, String password) {
        clickOnLoginLink();
        typeEmail(email);
        typePassword(password);
        clickOnLoginButton();
    }
}