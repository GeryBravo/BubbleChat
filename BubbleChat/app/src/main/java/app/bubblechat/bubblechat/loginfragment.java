package app.bubblechat.bubblechat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements OnClickListener {

    private Button signupBtn;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loginfragment, container, false);

        signupBtn = (Button) v.findViewById(R.id.signup);
        signupBtn.setOnClickListener(this);
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
