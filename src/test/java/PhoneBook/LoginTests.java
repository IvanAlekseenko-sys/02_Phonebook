package PhoneBook;

import PhoneBook.data.UserData;
import PhoneBook.model.User;
import PhoneBook.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {


    @BeforeMethod
    public void preCondition() {
        if (app.getUserHelper().isSignOutButtonPresent()) {
            logger.info("User already logged in. Sign out...");
            app.getUserHelper().clickOnSignOutButton();
        } else {
            logger.info("LOGIN link is present. No need to Sign Out.");
        }
    }

    //"ppp@ppp.com"
    //"Password@1"
    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class)
    public void loginExistedUserDataProviderPositiveTests(String email, String password) {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail(email);
        app.getUserHelper().typePassword(password);
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin(By.xpath("//button[.='Sign Out']"));
    }


    @Test
    public void loginExistedDataUserPositiveTests() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail(UserData.VALID_EMAIL);
        app.getUserHelper().typePassword(UserData.VALID_PASSWORD);
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
        Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }

    @AfterMethod
    public void postCondition() {
        //app.getUserHelper().clickOnSignOutButton();
    }

    @Test
    public void loginExistedUserPositiveTests() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail("ppp@ppp.com");
        app.getUserHelper().typePassword("Password@1");
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin(By.xpath("//button[.='Sign Out']"));
    }
}
