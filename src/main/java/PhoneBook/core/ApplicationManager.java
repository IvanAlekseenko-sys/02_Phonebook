package PhoneBook.core;

import PhoneBook.fw.ContactHelper;
import PhoneBook.fw.HomePageHelper;
import PhoneBook.fw.UserHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    UserHelper userHelper;
    ContactHelper contactHelper;
    HomePageHelper homePageHelper;

    public void init() {
        driver = new ChromeDriver();
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        userHelper = new UserHelper(driver);
        contactHelper = new ContactHelper(driver);
        homePageHelper = new HomePageHelper(driver);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public HomePageHelper getHomePageHelper() {
        return homePageHelper;
    }

    public void stop() {
        driver.quit();
    }
}
