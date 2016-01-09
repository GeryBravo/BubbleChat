package app.bubblechat.bubblechat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.bubblechat.bubblechat.R;

/**
 * Created by gerybravo on 2016.01.08..
 */
public class ConnectionsFragment extends Fragment {

    private RecyclerView connectionsList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View v = inflater.inflate(R.layout.fragment_connections, container, false);

        //connectionsList = (RecyclerView) v.findViewById(R.id.rvConnectionsList);
       // manager = new LinearLayoutManager(getActivity());

        return v;
    }
}
