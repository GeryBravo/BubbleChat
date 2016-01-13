package app.bubblechat.bubblechat.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import app.bubblechat.bubblechat.ConnectionsActivity;
import app.bubblechat.bubblechat.R;
import app.bubblechat.bubblechat.helpers.Metadata;
import app.bubblechat.bubblechat.objects.UserProfile;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private Button signupBtn;
    private Button loginBtn;
    private String usernametxt;
    private String passwordtxt;
    private EditText password;
    private EditText username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        if (ParseUser.getCurrentUser() != null)
        {
            Log.d("teszt", ParseUser.getCurrentUser().getUsername().toString());
            Intent intent = new Intent(
                    getActivity(),
                    ConnectionsActivity.class);
            startActivity(intent);
        }
        //Parse.initialize(getActivity());
        //ParseInstallation.getCurrentInstallation().saveInBackground();
        username = (EditText) v.findViewById(R.id.username);
        password = (EditText) v.findViewById(R.id.password);
        loginBtn = (Button) v.findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                logIn();
            }
        });
        signupBtn = (Button) v.findViewById(R.id.signup);
        signupBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new RegisterFragment());
                ft.commit();
            }
        });
        return v;
    }

    private void logIn() {
        ParseUser.logInInBackground(usernametxt, passwordtxt, new LogInCallback() {
            @Override
            public void done(ParseUser user, com.parse.ParseException e) {
                if (e == null) {
                    UserProfile.setUsername(usernametxt);
                    UserProfile.setsUserId(ParseUser.getCurrentUser().getObjectId());
                    setStateInDB();
                } else {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setStateInDB()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Metadata.USERS_TABLE);
        query.whereEqualTo(Metadata.ATTRIBUTE_NAME, UserProfile.getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> records, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved data!");
                    ParseObject temp = records.get(0);
                    temp.put(Metadata.ATTRIBUTE_STATE,true);
                    temp.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                            {
                                Intent intent = new Intent(getActivity(), ConnectionsActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Log.d(TAG, "Error: " + e.getMessage());
                            }
                        }
                    });

                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
    }
}
