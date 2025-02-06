package AlzaDe;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void CreateAccountPositiveTests() {
        type(By.cssSelector("#userName"), "pppaaa@ppp.com");
        type(By.cssSelector("#password"),"Password@1");
        click(By.cssSelector("#loginButtonText"));
        Assert.assertTrue(isElementPresent(By.xpath("//span[@title='Ananas Ananasov']")));
    }
}
