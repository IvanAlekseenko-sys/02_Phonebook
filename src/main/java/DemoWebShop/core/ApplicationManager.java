package DemoWebShop.core;

import DemoWebShop.fw.UserHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    UserHelper userHelper;

    public void init() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        userHelper = new UserHelper(driver);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public void stop() {
        driver.findElement(By.cssSelector("li#topcartlink>a>span")).click();
        driver.findElement(By.cssSelector("input[name='removefromcart']")).click();
        driver.findElement(By.cssSelector("input[name='updatecart']")).click();
        driver.quit();
    }

}
