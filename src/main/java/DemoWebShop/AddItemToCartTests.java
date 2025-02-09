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
        login();
    }

    @Test
    public void AddItemToCartPositiveTests() {
        try {
            click(By.xpath("(//input[@value='Add to cart'])[2]"));
            click(By.cssSelector("span.close"));
        } catch (Exception e) {
            System.out.println("Товар не был добавлен в корзину");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartItemCount = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("span.cart-qty")));

        Assert.assertEquals(cartItemCount.getText(), "(1)");
    }

}
