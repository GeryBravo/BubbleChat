package app.bubblechat.bubblechat.objects;

/**
 * Created by gerybravo on 2016.01.11..
 */
public final class UserProfile {

    private static String username;

    public static String getsUserId() {
        return sUserId;
    }

    public static void setsUserId(String sUserId) {
        UserProfile.sUserId = sUserId;
    }

    private static String sUserId;

    private UserProfile(){}

    public static String getUsername() {
        return username;
    }
    public static void setUsername(String username) {
        UserProfile.username = username;
    }
}
