package PhoneBook;

import PhoneBook.data.UserData;
import PhoneBook.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CreateAccountTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new WebDriverWait(app.driver, Duration.ofMillis(10));
        if (app.getUserHelper().isSignOutButtonPresent()) {
            logger.info("User already logged in. Sign out...");
            app.getUserHelper().clickOnSignOutButton();
        } else {
            logger.info("LOGIN link is present. No need to Sign Out.");
        }
    }

    @Test
    public void CreateAccountPositiveTest() {
        //click Login link //a[=.'LOGIN']
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        //enter email By.name("email")
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(UserData.generateRandomEmail());
        //enter password By.name("password")
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        //click 'Registration' button By.name("registration")
        app.driver.findElement(By.name("registration")).click();
        //Assert Sign out button //button[.='Sign Out']
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));
        //Assert.assertFalse(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test(dataProvider = "createAccountDataProvider", dataProviderClass = DataProviders.class)
    public void CreateAccountDataPositiveTest(String email, String password) {
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(email);
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(password);
        app.driver.findElement(By.name("registration")).click();
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));
        //Assert.assertFalse(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void CreateAccountNegativeTest() {
        //click Login link //a[=.'LOGIN']
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(UserData.VALID_EMAIL);
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        app.driver.findElement(By.name("registration")).click();
        app.getContactHelper().isAlertPresent();
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void CreateAccountNegativeSoftAssertTest() {
        SoftAssert softAssert = new SoftAssert();
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(UserData.VALID_EMAIL);
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        app.driver.findElement(By.name("registration")).click();
        softAssert.assertTrue(app.getContactHelper().isAlertPresent());
        softAssert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertAll();
    }
}

