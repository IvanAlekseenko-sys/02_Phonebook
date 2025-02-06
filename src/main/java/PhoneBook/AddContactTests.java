package PhoneBook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        login("ppp@ppp.com", "Password@1");
    }

    @Test
    public void addContactPositiveTests() {
        int contactsBefore = getContactsCount();
        System.out.println("Количество контактов ДО теста " + contactsBefore);
        addContactPositiveData(CONTACT_NAME);
        int contactsAfter = getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста " + contactsAfter);
        Assert.assertTrue(isContactAdded(CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @AfterMethod
    public void postCondition() {
        //сколько контактов на странице
        int contactsBefore = getContactsCount();
        System.out.println("Количество контактов ДО удаления " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        if (contactsBefore == 0) {
            System.out.println("Количество контактов 0. Нечего удалять");
            return;
        }
        click(By.className(CONTACT_LOCATOR));
        click(By.xpath(" //button[.='Remove']"));
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> getContactsCount() < contactsBefore);
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsBefore - 1));
        int contactsAfter = getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ удаления " + contactsAfter);
        //Проверяем, что стало на -1 контакт
        Assert.assertEquals(contactsAfter, contactsBefore - 1);
        System.out.println("Удаление контакта прошло успешно");
    }

}
