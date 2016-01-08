package app.bubblechat.bubblechat;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import app.bubblechat.bubblechat.fragments.ConnectionsFragment;

public class ConnectionsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connections_container);

        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
                return;

            ConnectionsFragment firstFragment = new ConnectionsFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }


    }
}
