package PhoneBook;

import PhoneBook.data.ContactData;
import PhoneBook.data.UserData;
import PhoneBook.model.Contact;
import PhoneBook.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static PhoneBook.fw.ContactHelper.CONTACT_LOCATOR;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (app.getUserHelper().isSignOutButtonPresent()) {
            logger.info("User already logged in. Sign out...");
            app.getUserHelper().clickOnSignOutButton();
        } else {
            logger.info("LOGIN link is present. No need to Sign Out.");
        }
        app.getUserHelper().login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);
    }

    @Test
    public void addContactPositiveTests() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName("Name")
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("ppp@ppp.com")
                .setAddress("Budapest, Hungary")
                .setDescription("My Contact Test"));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test(dataProvider = "addContactDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataPositiveTests(String contactName, String contactLastname, String validPhoneNumber, String validEmail, String address, String description) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(contactName)
                .setLastName(contactLastname)
                .setPhone(validPhoneNumber)
                .setEmail(validEmail)
                .setAddress(address)
                .setDescription(description));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test(dataProvider = "iteratorDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataIteratorPositiveTests(String contactName, String contactLastname, String validPhoneNumber, String validEmail, String address, String description) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(contactName)
                .setLastName(contactLastname)
                .setPhone(validPhoneNumber)
                .setEmail(validEmail)
                .setAddress(address)
                .setDescription(description));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test(dataProvider = "objectDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderObjectPositiveTest(Contact contact) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test(dataProvider = "addContactFromCsv", dataProviderClass = DataProviders.class)
    public void addContactDataProviderFromCSVPositiveTest(Contact contact) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }


    @Test
    public void addContactWoDescriptionPositiveTests() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName("Name")
                .setLastName("LastName")
                .setPhone("1234567890")
                //.setEmail("ppp@ppp.com")
                .setAddress("Budapest, Hungary"));
        //.setDescription("My Contact Test"));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test
    public void addContactDataWoDescriptionPositiveTests() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.CONTACT_NAME)
                .setLastName(ContactData.CONTACT_LASTNAME)
                .setPhone(ContactData.VALID_PHONE_NUMBER)
                //.setEmail(UserData.VALID_EMAIL)
                .setAddress(ContactData.ADDRESS));
        //.setDescription(ContactData.DESCRIPTION));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.CONTACT_NAME));
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
