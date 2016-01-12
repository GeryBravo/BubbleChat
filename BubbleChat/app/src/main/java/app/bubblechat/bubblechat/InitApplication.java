package app.bubblechat.bubblechat;

import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by gerybravo on 2016.01.11..
 */
public class InitApplication extends android.app.Application {
    @Override
    public void onCreate()
    {
        super.onCreate();

        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
