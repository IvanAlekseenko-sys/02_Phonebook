package DemoWebShop.fw;

import DemoWebShop.core.BaseHelper;
import DemoWebShop.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void fillInLoginForm(User user) {
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
    }

    public void typePassword(String password) {
        type(By.id("Password"), password);
    }

    public void typeEmail(String email) {
        type(By.id("Email"), email);
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[contains(text(),'Log in')]"));
    }

    public void login() {
        clickOnLoginLink();
        fillInLoginForm(new User()
                .setEmail("ppp@ppp.com")
                .setPassword("Password@1"));
        click(By.cssSelector("input[value='Log in']"));
    }
}
