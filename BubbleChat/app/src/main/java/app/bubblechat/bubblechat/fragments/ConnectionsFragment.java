package app.bubblechat.bubblechat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.*;

import app.bubblechat.bubblechat.R;
import app.bubblechat.bubblechat.helpers.Metadata;

/**
 * Created by gerybravo on 2016.01.08..
 */
public class ConnectionsFragment extends Fragment {

    private static final String TAG = "ConnectionsFragment";

    private String username = "jolanka"; //test name!!!!

    /*private RecyclerView connectionsList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View v = inflater.inflate(R.layout.fragment_connections, container, false);

        /*
        connectionsList = (RecyclerView) v.findViewById(R.id.rvConnectionsList);
        manager = new LinearLayoutManager(getActivity());
        connectionsList.setLayoutManager(manager);*/

        //get dataset
        //TODO adapter
        Parse.initialize(getActivity());/*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
        getFriendsList(username);
        return v;
    }

    private List<String> getFriendsList(String username)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("users");
        query.whereEqualTo("name", "awadon");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> records, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved data!");
                    String friends = records.get(0).getString("friends");
                    Log.d(TAG,friends);
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
        return new ArrayList<String>();
    }
}
