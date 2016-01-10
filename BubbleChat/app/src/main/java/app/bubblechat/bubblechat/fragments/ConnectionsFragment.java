package app.bubblechat.bubblechat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import app.bubblechat.bubblechat.R;
import app.bubblechat.bubblechat.RegisterFragment;

/**
 * Created by gerybravo on 2016.01.08..
 */
public class ConnectionsFragment extends Fragment {

    private RecyclerView connectionsList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private ImageButton bNewMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View v = inflater.inflate(R.layout.fragment_connections, container, false);
        bNewMessage = (ImageButton) v.findViewById(R.id.bNewMessage);
        bNewMessage.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v)
            {
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new ChatFragment());
                ft.commit();
            }
        });
        //connectionsList = (RecyclerView) v.findViewById(R.id.rvConnectionsList);
       // manager = new LinearLayoutManager(getActivity());

        return v;
    }
}
