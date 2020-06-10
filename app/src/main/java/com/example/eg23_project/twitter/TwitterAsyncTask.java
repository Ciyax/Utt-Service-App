package com.example.eg23_project.twitter;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eg23_project.PracticalInfTwitter;
import com.example.eg23_project.PracticalInfTwitterAdapter;
import com.example.eg23_project.R;

import java.util.ArrayList;

public class TwitterAsyncTask extends AsyncTask<Object, Void, ArrayList<TwitterTweet>> {

    final static String TWITTER_API_KEY = "xghFGJu7h0iZuIc1zf95OMrrL";
    final static String TWITTER_API_SECRET = "GPjq02nkm7kYyUV9pYR6yumh5CWkNonU6vrBcmGR3nxbuSiHtN";

    private RecyclerView recyclerView;
    private PracticalInfTwitter.OnListFragmentInteractionListener mListener;

    @Override
    protected ArrayList<TwitterTweet> doInBackground(Object... params) {
        ArrayList<TwitterTweet> twitterTweets = null;
        recyclerView = (RecyclerView) params[1];
        mListener = (PracticalInfTwitter.OnListFragmentInteractionListener) params[2];
        if (params.length > 0) {
            TwitterAPI twitterAPI = new TwitterAPI(TWITTER_API_KEY, TWITTER_API_SECRET);
            twitterTweets = twitterAPI.getTwitterTweets(params[0].toString());
        }
        return twitterTweets;
    }

    @Override
    protected void onPostExecute(ArrayList<TwitterTweet> twitterTweets) {
        recyclerView.setAdapter(new PracticalInfTwitterAdapter(twitterTweets, mListener));;
        //ListView lv = callerActivity.getListView();
        //lv.setDividerHeight(0);
        //lv.setDivider(this.getResources().getDrawable(android.R.color.transparent));
        //lv.setBackgroundColor(callerActivity.getResources().getColor(R.color.twitter_blue));
    }
}
