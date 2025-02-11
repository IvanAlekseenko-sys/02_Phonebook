package PhoneBook;

import PhoneBook.data.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static PhoneBook.fw.ContactHelper.CONTACT_LOCATOR;

public class DeleteContactsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (app.getUserHelper().isSignOutButtonPresent()) {
            logger.info("User already logged in. Sign out...");
            app.getUserHelper().clickOnSignOutButton();
        } else {
            logger.info("LOGIN link is present. No need to Sign Out.");
        }
        app.getUserHelper().login("ppp@ppp.com", "Password@1");
        app.getContactHelper().addContactPositiveData(ContactData.CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContactTests() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        app.getContactHelper().clickAndDeleteOneContact();
        new WebDriverWait(app.driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsBefore - 1));
        int contactsAfter = app.getContactHelper().getContactsCount();
        Assert.assertEquals(contactsAfter, contactsBefore - 1);
    }

    @Test
    public void DeleteAllContactsTests() {
        try {
            while (app.getContactHelper().hasContacts()) {
                app.getContactHelper().deleteFirstContact();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Нет контактов для удаления");
        }
        Assert.assertEquals(app.getContactHelper().getContactsCount(), 0, "Не все контакты были удалены");
    }
}
