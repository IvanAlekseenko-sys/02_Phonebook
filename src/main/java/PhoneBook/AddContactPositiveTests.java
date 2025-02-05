package PhoneBook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactPositiveTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        login("ppp@ppp.com", "Password@1");
    }

    @Test
    public void addContactPositiveTests() {

    }
}
