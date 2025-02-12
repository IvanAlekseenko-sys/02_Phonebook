package DemoWebShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddItemToCartTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.getUserHelper().login();
    }

    @Test
    public void AddItemToCartPositiveTests() {
        String assertText = app.driver.findElement(By.xpath("(//h2[@class='product-title']//a)[2]")).getText();
        System.out.println(assertText);
        try {
            app.getUserHelper().click(By.xpath("(//input[@value='Add to cart'])[2]"));
            app.getUserHelper().click(By.cssSelector("span.close"));
        } catch (Exception e) {
            System.out.println("Товар не был добавлен в корзину");
        }
        WebDriverWait wait = new WebDriverWait(app.driver, Duration.ofSeconds(10));
        WebElement cartLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li#topcartlink>a>span")));
        app.driver.findElement(By.cssSelector("li#topcartlink>a>span")).click();
        new WebDriverWait(app.driver, Duration.ofSeconds(10));
        String assertText2 = app.driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]")).getText();
        Assert.assertEquals(assertText2, assertText);
        //Assert.assertEquals(cartItemCount.getText(), "(1)");
    }


    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
