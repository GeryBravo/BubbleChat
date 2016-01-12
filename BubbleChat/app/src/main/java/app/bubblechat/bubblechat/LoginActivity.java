package app.bubblechat.bubblechat;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import app.bubblechat.bubblechat.fragments.LoginFragment;

public class LoginActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            LoginFragment f = new LoginFragment();
            f.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, f).commit();
        }
    }
}
