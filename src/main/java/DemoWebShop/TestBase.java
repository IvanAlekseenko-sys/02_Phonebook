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
        typeEmail();
        typePassword();
        click(By.cssSelector("input[value='Log in']"));
    }

    public void typePassword() {
        driver.findElement(By.cssSelector("#Password")).click();
        driver.findElement(By.cssSelector("#Password")).clear();
        driver.findElement(By.cssSelector("#Password")).sendKeys("Password@1");
    }

    public void typeEmail() {
        driver.findElement(By.cssSelector("#Email")).click();
        driver.findElement(By.cssSelector("#Email")).clear();
        driver.findElement(By.cssSelector("#Email")).sendKeys("ppp@ppp.com");
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[contains(text(),'Log in')]"));
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }
}
