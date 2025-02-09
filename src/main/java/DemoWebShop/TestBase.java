package DemoWebShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        driver.findElement(By.cssSelector("li#topcartlink>a>span")).click();
        driver.findElement(By.cssSelector("input[name='removefromcart']")).click();
        driver.findElement(By.cssSelector("input[name='updatecart']")).click();
        driver.quit();
    }

    public void login() {
        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("ppp@ppp.com")
                .setPassword("Password@1"));
        click(By.cssSelector("input[value='Log in']"));
    }


    public void fillInLoginForm(User user) {
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
    }

    public void typePassword(String password) {
        type(By.id("Password"), password);
    }

    public void typeEmail(String email) {
        type(By.id("Email"), email);
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }


    public void clickOnLoginLink() {
        click(By.xpath("//a[contains(text(),'Log in')]"));
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }
}
