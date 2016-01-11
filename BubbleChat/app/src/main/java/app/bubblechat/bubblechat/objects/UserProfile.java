package app.bubblechat.bubblechat.objects;

/**
 * Created by gerybravo on 2016.01.11..
 */
public final class UserProfile {

    private static String username;

    private UserProfile(){}

    public static String getUsername() {
        return username;
    }
    public static void setUsername(String username) {
        UserProfile.username = username;
    }
}
