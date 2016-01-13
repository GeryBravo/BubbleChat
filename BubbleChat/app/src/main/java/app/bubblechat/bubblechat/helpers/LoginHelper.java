package app.bubblechat.bubblechat.helpers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Ben on 2016.01.12..
 */
public class LoginHelper{

    private Pattern patternUsername, patternPassword;
    private Matcher matcher;

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{4,15}$";
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,15}$";


    public LoginHelper(){
        patternUsername = Pattern.compile(USERNAME_PATTERN);
        patternPassword = Pattern.compile(PASSWORD_PATTERN);
    }
    public boolean validateUsername(final String username){
        matcher = patternUsername.matcher(username);
        return matcher.matches();
    }
    public boolean validatePassword(final String password) {
        matcher = patternPassword.matcher(password);
        return matcher.matches();
    }
}