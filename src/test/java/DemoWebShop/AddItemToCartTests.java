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

    @BeforeMethod(dependsOnMethods = "setUp")
    public void precondition() {
        app.getUserHelper().login();
    }

    @Test
    public void AddItemToCartPositiveTests() {
        try {
            app.getUserHelper().click(By.xpath("(//input[@value='Add to cart'])[2]"));
            app.getUserHelper().click(By.cssSelector("span.close"));
        } catch (Exception e) {
            System.out.println("Товар не был добавлен в корзину");
        }
        WebDriverWait wait = new WebDriverWait(app.driver, Duration.ofSeconds(10));
        WebElement cartItemCount = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("span.cart-qty")));

        Assert.assertEquals(cartItemCount.getText(), "(1)");
    }


    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
