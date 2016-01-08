package app.bubblechat.bubblechat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.bubblechat.bubblechat.R;

/**
 * Created by gerybravo on 2016.01.08..
 */
public class UserProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        return inflater.inflate(R.layout.fragment_userprofile, container, false);
    }
}
