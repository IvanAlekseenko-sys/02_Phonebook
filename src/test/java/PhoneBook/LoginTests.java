package PhoneBook;

import PhoneBook.model.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginExistedUserPositiveTests() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail("ppp@ppp.com");
        app.getUserHelper().typePassword("Password@1");
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin(By.xpath("//button[.='Sign Out']"));
    }

    @Test
    public void loginWoEmailNegativeTests() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().fillInLoginForm(new User()
                //.setEmail("ppp@ppp.com")
                .setPassword("Password@1"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isAlertPresent());    }

}
