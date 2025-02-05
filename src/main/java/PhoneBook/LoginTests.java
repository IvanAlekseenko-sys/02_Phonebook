package PhoneBook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginExistedUserPositiveTests() {
        clickOnLoginLink();
        typeEmail("ppp@ppp.com");
        typePassword("Password@1");
        clickOnLoginButton();
        checkLogin(By.xpath("//button[.='Sign Out']"));
    }
}
