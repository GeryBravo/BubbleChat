package app.bubblechat.bubblechat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import app.bubblechat.bubblechat.helpers.MessageType;
import app.bubblechat.bubblechat.objects.ChatListAdapter;
import app.bubblechat.bubblechat.objects.Message;
import app.bubblechat.bubblechat.R;
import app.bubblechat.bubblechat.objects.UserProfile;

/**
 * Created by Ben on 2016.01.10..
 */
public class ChatFragment extends Fragment {

    private Handler handler = new Handler();
    private Button bSend;
    private EditText tMessage;
    private EditText tAdress;
    private ListView lvChat;
    private ArrayList<Message> mMessages;
    private ChatListAdapter mAdapter;
    // Keep track of initial load to scroll to the bottom of the ListView
    private boolean mFirstLoad;
    private static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        ParseObject.registerSubclass(Message.class);
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        if (ParseUser.getCurrentUser() != null) { // start with existing user
            setupMessagePosting(v);
        } else {
            //visszanavigálunk a login oldalra
        }

        handler.postDelayed(runnable, 100);
        return v;
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            refreshMessages();
            handler.postDelayed(this, 100);
        }
    };
    private void refreshMessages() {
        receiveMessage();
    }

    private void setupMessagePosting(View v) {
        tMessage = (EditText) v.findViewById(R.id.tMessage);
        tAdress = (EditText) v.findViewById(R.id.tAdress);
        bSend = (Button) v.findViewById(R.id.bSend);
        lvChat = (ListView) v.findViewById(R.id.lvChat);
        mMessages = new ArrayList<Message>();
        // Automatically scroll to the bottom when a data set change notification is received and only if the last item is already visible on screen. Don't scroll to the bottom otherwise.
        lvChat.setTranscriptMode(1);
        mFirstLoad = true;
        mAdapter = new ChatListAdapter(getActivity(), UserProfile.getsUserId(), mMessages);
        lvChat.setAdapter(mAdapter);
        bSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String body = tMessage.getText().toString();
        String adress = tAdress.getText().toString();
        // Use Message model to create new messages now
        Message message = new Message();
        message.setUserId(UserProfile.getsUserId());
        message.setAdressId(adress);
        message.setBody(body);
        message.setType(MessageType.MESSAGE);
        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                receiveMessage();
            }
        });
        tMessage.setText("");
    }

    private void receiveMessage() {

        // Construct query to execute
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        // Configure limit and sort order
        query.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);
        query.orderByAscending("createdAt");
        // Execute query to fetch all messages from Parse asynchronously
        // This is equivalent to a SELECT query with SQL
        query.findInBackground(new FindCallback<Message>() {
            public void done(List<Message> messages, ParseException e) {
                if (e == null) {
                    mMessages.clear();
                    Log.d("Messages:", mMessages.toString());

                    for (Message m : messages
                            ) {
                        if(m.getAdressId().equals(UserProfile.getUsername()))
                        {
                            mMessages.add(m);
                        }
                    }
                    mAdapter.notifyDataSetChanged(); // update adapter
                    // Scroll to the bottom of the list on initial load
                    if (mFirstLoad) {
                        lvChat.setSelection(mAdapter.getCount() - 1);
                        mFirstLoad = false;
                    }
                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });

    }
}