//klasa z ustawieniami do bazy danych, rozwiazanie podzadania 4
public class DBPropertie {
    private static final String url = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static final String user = "sdatest";
    private static final String password = "Start123!";

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
