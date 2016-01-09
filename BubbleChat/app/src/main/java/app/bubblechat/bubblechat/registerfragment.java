package app.bubblechat.bubblechat;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private Button signupBtn;
    private String usernametxt;
    private String passwordtxt;
    private String emailtxt;
    private EditText password;
    private EditText username;
    private EditText email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registerfragment, container, false);
        username = (EditText) v.findViewById(R.id.username);
        password = (EditText) v.findViewById(R.id.password);
        email = (EditText) v.findViewById(R.id.email);

        signupBtn = (Button) v.findViewById(R.id.signup);
        signupBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
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
                            Toast.makeText(getActivity(),
                                    "Successfully Signed up, please log in.",
                                    Toast.LENGTH_LONG).show();
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
