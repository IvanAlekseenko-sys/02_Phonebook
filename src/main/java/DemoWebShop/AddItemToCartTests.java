package DemoWebShop;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddItemToCartTests extends TestBase {

    @BeforeMethod(dependsOnMethods = "setUp")
    public void precondition() {
        login();
    }

    @Test
    public void AddItemToCartPositiveTests() {
        try {
            click(By.xpath("(//input[@value='Add to cart'])[2]"));
            click(By.xpath("//body/div[@id='bar-notification']/span[1]"));
        } catch (Exception e) {
            System.out.println("Товар не был добавлен в корзину");
        }
        Assert.assertEquals(driver.findElement(By.xpath("//span[normalize-space(text())='(1)']")), driver.findElement(By.xpath("//span[normalize-space(text())='(1)']")));

    }

}
