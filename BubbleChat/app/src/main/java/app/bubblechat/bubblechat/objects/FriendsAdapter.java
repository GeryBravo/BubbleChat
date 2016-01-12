package app.bubblechat.bubblechat.objects;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import app.bubblechat.bubblechat.R;

/**
 * Created by gerybravo on 2016.01.10..
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{
    private FriendData[] itemsData;

    public FriendsAdapter(List<FriendData> friendDataList) {
        itemsData = new FriendData[friendDataList.size()];
        friendDataList.toArray(itemsData);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recv_item, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        viewHolder.button.setText(itemsData[position].getName());
        int imgResourceOnline = R.drawable.online;
        int imgResourceOffline = R.drawable.offline;
        if(!itemsData[position].isState()) {
            viewHolder.button.setCompoundDrawablesWithIntrinsicBounds(0, 0, imgResourceOffline,0);
            viewHolder.button.setEnabled(false);
        }
        else
        {
            viewHolder.button.setCompoundDrawablesWithIntrinsicBounds(0,0,imgResourceOnline,0);
        }
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public Button button;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            button = (Button) itemLayoutView.findViewById(R.id.bItem);

        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.length;
    }
}
