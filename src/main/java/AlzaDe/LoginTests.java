package AlzaDe;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void LoginPositiveTests() {
        type(By.id("userName"), "pppaaa@ppp.com");
        type(By.id("password"),"Password@1");
        click(By.id("loginButtonText"));
        Assert.assertTrue(isElementPresent(By.xpath("//span[@title='Ananas Ananasov']")));
    }
}
