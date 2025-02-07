package PhoneBook;

import PhoneBook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static PhoneBook.fw.ContactHelper.CONTACT_LOCATOR;
import static PhoneBook.fw.ContactHelper.CONTACT_NAME;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().login("ppp@ppp.com", "Password@1");
    }

    @Test
    public void addContactPositiveTests() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(CONTACT_NAME)
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("ppp@ppp.com")
                .setAddress("Budapest, Hungary")
                .setDescription("My Contact Test"));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test
    public void addContactWoDescriptionPositiveTests() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(CONTACT_NAME)
                .setLastName("LastName")
                .setPhone("1234567890")
                //.setEmail("ppp@ppp.com")
                .setAddress("Budapest, Hungary"));
        //.setDescription("My Contact Test"));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @AfterMethod
    public void postCondition() {
        //сколько контактов на странице
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО удаления " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        if (contactsBefore == 0) {
            System.out.println("Количество контактов 0. Нечего удалять");
            return;
        }
        app.getContactHelper().click(By.className(CONTACT_LOCATOR));
        app.getContactHelper().click(By.xpath(" //button[.='Remove']"));
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> getContactsCount() < contactsBefore);
        new WebDriverWait(app.driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsBefore - 1));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ удаления " + contactsAfter);
        //Проверяем, что стало на -1 контакт
        Assert.assertEquals(contactsAfter, contactsBefore - 1);
        System.out.println("Удаление контакта прошло успешно");
    }

}
