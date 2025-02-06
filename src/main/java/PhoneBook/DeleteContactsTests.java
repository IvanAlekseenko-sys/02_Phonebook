package PhoneBook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DeleteContactsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        login("ppp@ppp.com", "Password@1");
        addContactPositiveData(CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContactTests() {
        int contactsBefore = getContactsCount();
        clickAndDeleteOneContact();
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsBefore - 1));
        int contactsAfter = getContactsCount();
        Assert.assertEquals(contactsAfter, contactsBefore - 1);
    }

    @Test
    public void DeleteAllContactsTests() {
        try {
            while (hasContacts()) {
                deleteFirstContact();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Нет контактов для удаления");
        }
        Assert.assertEquals(getContactsCount(),0, "Не все контакты были удалены");
    }
}
