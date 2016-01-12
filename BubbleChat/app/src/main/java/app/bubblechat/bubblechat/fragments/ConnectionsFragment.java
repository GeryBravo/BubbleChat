package app.bubblechat.bubblechat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

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
import app.bubblechat.bubblechat.objects.UserProfile;

/**
 * Created by gerybravo on 2016.01.08..
 */
public class ConnectionsFragment extends Fragment {

    private static final String TAG = "ConnectionsFragment";

    private List<String> friendsName;
    private List<Boolean> friendsState;
    private List<FriendData> friendData;
    private ImageButton bNewMessage;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FriendsAdapter friendsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View v = inflater.inflate(R.layout.fragment_connections, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rvConnectionsList);
        friendsName = new ArrayList<String>();
        friendsState = new ArrayList<Boolean>();
        Log.d("lofasz","belepunk a fragmentbe");
        //Parse.initialize(getActivity());
        getFriendsList();
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        bNewMessage = (ImageButton) v.findViewById(R.id.bNewMessage);
        bNewMessage.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new ChatFragment());
                ft.commit();
            }
        });
        return v;
    }

    private void getFriendsList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Metadata.USERS_TABLE);
        query.whereEqualTo(Metadata.ATTRIBUTE_NAME, UserProfile.getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> records, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved data!");
                    handleNames(records);
                    getFriendsState(friendsName);
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });
        Log.d("lofasz","megkapjuk a barát listát");
    }

    private void getFriendsState(List<String> friendsName) {
        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        for(int i = 0; i < friendsName.size(); i++)
        {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(Metadata.USERS_TABLE);
            query.whereEqualTo(Metadata.ATTRIBUTE_NAME,friendsName.get(i));
            queries.add(query);
        }
        ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);
        mainQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> results, ParseException e) {
                if(e==null)
                {
                    Log.d(TAG, "Retrieved data!");
                    handleStates(results);
                }
                else
                {
                    Log.d(TAG,e.getMessage());
                }
            }
        });
        Log.d("lofasz","megkapjuk a barátok állapotait is");
    }

    private void handleNames(List<ParseObject> response)
    {
        String friends = response.get(0).getString(Metadata.ATTRIBUTE_FRIENDSLIST);
        String[] temp = friends.split(",");
        for (int i = 0; i < temp.length; i++) {
            Log.d(TAG, temp[i]);
            friendsName.add(temp[i]);
            Log.d(TAG, String.valueOf(friendsName.size()));
        }
    }

    private void handleStates(List<ParseObject> response)
    {
        Log.d(TAG,"handleStates");
        for(int i = 0; i < response.size(); i++)
        {
            boolean state = response.get(i).getBoolean(Metadata.ATTRIBUTE_STATE);
            friendsState.add(state);
        }
        buildRecyclerView();
    }

    private void buildRecyclerView()
    {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        friendData = genRecyclerViewItems(friendsName,friendsState);
        friendsAdapter = new FriendsAdapter(friendData);
        recyclerView.setAdapter(friendsAdapter);
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
