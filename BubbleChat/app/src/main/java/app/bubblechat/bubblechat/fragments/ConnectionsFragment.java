package app.bubblechat.bubblechat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.*;

import app.bubblechat.bubblechat.R;
import app.bubblechat.bubblechat.helpers.Metadata;
import app.bubblechat.bubblechat.objects.FriendData;
import app.bubblechat.bubblechat.objects.FriendsAdapter;

/**
 * Created by gerybravo on 2016.01.08..
 */
public class ConnectionsFragment extends Fragment {

    private static final String TAG = "ConnectionsFragment";

    private String username = "jolanka"; //test name!!!!
    private List<String> friendsName;
    private List<Boolean> friendsState;

    private RecyclerView recyclerView;
    private List<FriendData> friendData;
    private FriendsAdapter friendsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View v = inflater.inflate(R.layout.fragment_connections, container, false);
        friendsName = new ArrayList<String>();
        friendsState = new ArrayList<Boolean>();
        Parse.initialize(getActivity());
        getFriendsList();
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    private void getFriendsList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Metadata.USERS_TABLE);
        query.whereEqualTo(Metadata.ATTRIBUTE_NAME, username);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> records, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved data!");
                    String friends = records.get(0).getString(Metadata.ATTRIBUTE_FRIENDSLIST);
                    String[] temp = friends.split(",");
                    for(int i = 0; i < temp.length; i++)
                    {
                        Log.d(TAG,temp[i]);
                        friendsName.add(temp[i]);
                        Log.d(TAG,String.valueOf(friendsName.size()));
                    }
                    getFriendsState(friendsName);
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
    }

    private void getFriendsState(List<String> friendsName) {
            friendsName.add("awadon");
            ParseQuery<ParseObject> query = ParseQuery.getQuery(Metadata.USERS_TABLE);
            query.whereEqualTo(Metadata.ATTRIBUTE_NAME, friendsName.get(0));
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> records, ParseException e) {
                    if (e == null) {
                        Log.d(TAG, "Retrieved data!");
                        boolean state = records.get(0).getBoolean("state");
                        friendsState.add(state);
                        Log.d("cucc",String.valueOf(friendsState.size()));
                        //recyclerView = (RecyclerView) v.findViewById(R.id.rvConnectionsList);
                        //friendData = genRecyclerViewItems(friendsName, friendsState);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        friendsAdapter = new FriendsAdapter(friendData);
                        recyclerView.setAdapter(friendsAdapter);
                    } else {
                        Log.d(TAG, "Error: " + e.getMessage());
                    }
                }
            });

    }

    private List<FriendData> genRecyclerViewItems(List<String> friendsName, List<Boolean> friendsState) {
        List<FriendData> friendDataList = new ArrayList<FriendData>();
        for(int i = 0; i < friendsName.size(); i++)
        {
            FriendData fd = new FriendData(friendsName.get(i),friendsState.get(i));
            friendDataList.add(fd);
        }
        return friendDataList;
    }
}
