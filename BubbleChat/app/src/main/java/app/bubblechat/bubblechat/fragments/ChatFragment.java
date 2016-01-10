package app.bubblechat.bubblechat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.bubblechat.bubblechat.R;
import app.bubblechat.bubblechat.RegisterFragment;

/**
 * Created by Ben on 2016.01.10..
 */
public class ChatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}