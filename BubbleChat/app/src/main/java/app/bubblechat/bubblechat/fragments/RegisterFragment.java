package app.bubblechat.bubblechat.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import app.bubblechat.bubblechat.R;
import app.bubblechat.bubblechat.helpers.LoginHelper;

public class RegisterFragment extends Fragment {

    private Button signupBtn;
    private String usernametxt;
    private String passwordtxt;
    private String emailtxt;
    private String passwordagaintxt;
    private EditText password;
    private EditText passwordagain;
    private EditText username;
    private EditText email;
    private LoginHelper loginHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        username = (EditText) v.findViewById(R.id.username);
        password = (EditText) v.findViewById(R.id.password);
        passwordagain = (EditText) v.findViewById(R.id.passwordagain);
        email = (EditText) v.findViewById(R.id.email);


        signupBtn = (Button) v.findViewById(R.id.signup);
        signupBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                LoginHelper loginHelper = new LoginHelper();
                if (!loginHelper.validateUsername(username.getText().toString())) {
                    username.setError(getString(R.string.ErrorUsername));
                    return;
                }
                if (!loginHelper.validatePassword(password.getText().toString())) {
                    password.setError(getString(R.string.ErrorPassword));
                    return;
                }
                if (!TextUtils.equals(password.getText().toString(), passwordagain.getText().toString())) {
                    password.setError(getString(R.string.ErrorPasswordNoMatch));
                    return;
                }
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                emailtxt = email.getText().toString();

                ParseUser user = new ParseUser();
                user.setUsername(usernametxt);
                user.setPassword(passwordtxt);
                user.setEmail(emailtxt);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.fragment_container, new RegisterFragment());
                            ft.commit();
                        } else {
                            Toast.makeText(getActivity(),
                                    "Sign up Error", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
            }
        });

        return v;
    }
}
