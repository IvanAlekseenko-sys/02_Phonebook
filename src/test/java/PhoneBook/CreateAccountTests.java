package PhoneBook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {
    @Test
    public void CreateAccountPositiveTest() {
        //click Login link //a[=.'LOGIN']
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        //enter email By.name("email")
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys("pqspap@ppp.com");
        //enter password By.name("password")
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys("Password@1");
        //click 'Registration' button By.name("registration")
        app.driver.findElement(By.name("registration")).click();
        //Assert Sign out button //button[.='Sign Out']
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));
        //Assert.assertFalse(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void CreateAccountNegativeTest() {
        //click Login link //a[=.'LOGIN']
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys("pqpap@ppp.com");
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys("Password@1");
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
        app.driver.findElement(By.name("email")).sendKeys("pqpap@ppp.com");
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys("Password@1");
        app.driver.findElement(By.name("registration")).click();
        softAssert.assertTrue(app.getContactHelper().isAlertPresent());
        softAssert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertAll();
    }

}

