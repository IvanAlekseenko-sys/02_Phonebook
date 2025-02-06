package DemoWebShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class AddItemToCartTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeMethod(dependsOnMethods = "setUp")
    public void precondition() {
        driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
        driver.findElement(By.cssSelector("#Email")).click();
        driver.findElement(By.cssSelector("#Email")).clear();
        driver.findElement(By.cssSelector("#Email")).sendKeys("ppp@ppp.com");
        driver.findElement(By.cssSelector("#Password")).click();
        driver.findElement(By.cssSelector("#Password")).clear();
        driver.findElement(By.cssSelector("#Password")).sendKeys("Password@1");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
    }

    @Test
    public void AddItemToCartPositiveTests() {
        driver.findElement(By.xpath("(//input[@value='Add to cart'])[2]")).click();
        driver.findElement(By.xpath("//body/div[@id='bar-notification']/span[1]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space(text())='(1)']")),driver.findElement(By.xpath("//span[normalize-space(text())='(1)']")));

    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        driver.findElement(By.cssSelector("li#topcartlink>a>span")).click();
        driver.findElement(By.cssSelector("input[name='removefromcart']")).click();
        driver.findElement(By.cssSelector("input[name='updatecart']")).click();
        driver.quit();
    }
}
