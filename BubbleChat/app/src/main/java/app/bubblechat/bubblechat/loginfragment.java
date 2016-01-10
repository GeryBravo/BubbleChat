package app.bubblechat.bubblechat;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements OnClickListener {

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
        View v = inflater.inflate(R.layout.fragment_loginfragment, container, false);

        signupBtn = (Button) v.findViewById(R.id.signup);
        signupBtn.setOnClickListener(this);
        Parse.initialize(getActivity(), "ZE4vcFbwtaV2KiG3WOt4RSj5jFYEKYNFgNGhVAU5", "3474sB1edGG5ozQLbwCNqYDNDEIGsgxs942ZuC7a");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        username = (EditText) v.findViewById(R.id.username);
        password = (EditText) v.findViewById(R.id.password);
        loginBtn = (Button) v.findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();

                ParseUser.logInInBackground(usernametxt, passwordtxt, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            Intent intent = new Intent(getActivity(),
                                    ConnectionsActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(
                                    getActivity(),
                                    "error occured: "+e,
                                    Toast.LENGTH_LONG).show();
                            //Log.d("LoginFragment", e.toString());
                            //Log.d("LoginFragment", usernametxt + passwordtxt);
                        }
                    }
                });
            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new RegisterFragment());
                ft.commit();

                break;
        }
    }
}
