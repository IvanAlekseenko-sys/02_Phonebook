package PhoneBook.fw;

import PhoneBook.core.BaseHelper;
import PhoneBook.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UserHelper extends BaseHelper {
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void typePassword(String password) {
        type(By.name("password"), password);
    }

    public void typeEmail(String email) {
        type(By.name("email"), email);
    }

    public void clickOnLoginButton() {
        click(By.name("login"));
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[.='LOGIN']"));
    }

    public void checkLogin(By locator) {
        Assert.assertTrue(isElementPresent(locator));
    }

    public void login(String email, String password) {
        clickOnLoginLink();
        typeEmail(email);
        typePassword(password);
        clickOnLoginButton();
    }

    public void fillInLoginForm(User user) {
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
    }
}
