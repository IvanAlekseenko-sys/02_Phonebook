package PhoneBook.data;

public class UserData {
    public static final String VALID_EMAIL = "ppp@ppp.com";
    public static final String VALID_PASSWORD = "Password@1";
    public static final String RANDOM_EMAIL = "ppp" + System.currentTimeMillis() + "@ppp.com";

    public static String generateRandomEmail() {
        return "ppp" + System.currentTimeMillis() + (int) (Math.random() * 1000) + "@ppp.com";
    }
}
