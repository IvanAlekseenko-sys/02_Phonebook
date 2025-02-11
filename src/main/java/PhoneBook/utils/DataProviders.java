package PhoneBook.utils;

import PhoneBook.data.UserData;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                {"ppp@ppp.com", "Password@1"},
                {"portishead2025@gmail.com", "Password@1"},
                {"portishead2026@gmail.com", "Password@1"}
        };
    }

    @DataProvider
    public Object[][] createAccountDataProvider() {
        return new Object[][]{
                {UserData.RANDOM_EMAIL, "Password@1"},
                //{"gfsgjs13h@dsd.com","Password@1"},
                //{"gfsgj21sh@dsd.com","Password@1"}
        };
    }
}

