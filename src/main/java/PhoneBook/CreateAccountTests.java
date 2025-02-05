package PhoneBook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {
    @Test
    public void CreateAccountPositiveTest() {
        //click Login link //a[=.'LOGIN']
        driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        //enter email By.name("email")
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("pqspap@ppp.com");
        //enter password By.name("password")
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Password@1");
        //click 'Registration' button By.name("registration")
        driver.findElement(By.name("registration")).click();
        //Assert Sign out button //button[.='Sign Out']
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
        //Assert.assertFalse(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void CreateAccountNegativeTest() {
        //click Login link //a[=.'LOGIN']
        driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("pqpap@ppp.com");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Password@1");
        driver.findElement(By.name("registration")).click();
        isAlertPresent();
        Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void CreateAccountNegativeSoftAssertTest() {
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("pqpap@ppp.com");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Password@1");
        driver.findElement(By.name("registration")).click();
        softAssert.assertTrue(isAlertPresent());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertAll();
    }

}

