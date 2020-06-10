package com.example.eg23_project;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eg23_project.PracticalInfTwitter.OnListFragmentInteractionListener;
import com.example.eg23_project.twitter.TwitterTweet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TwitterTweet} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PracticalInfTwitterAdapter extends RecyclerView.Adapter<PracticalInfTwitterAdapter.ViewHolder> {

    private final List<TwitterTweet> mValues;
    private final OnListFragmentInteractionListener mListener;

    public PracticalInfTwitterAdapter(List<TwitterTweet> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_twitter_actualities, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        try {
            DateFormat dateFormatFR = new SimpleDateFormat("dd MMMM yyyy - HH:mm", Locale.FRANCE);
            String dateFr = dateFormatFR.format(getTwitterDate(mValues.get(position).getCreatedAt()));
            holder.mMessageDate.setText(dateFr);
        } catch (ParseException e) {
            holder.mMessageDate.setText(mValues.get(position).getCreatedAt());
        }

        holder.mItem = mValues.get(position);
        holder.mUserScreenname.setText(mValues.get(position).getTwitterUser().getScreenName());
        holder.mMessage.setText(mValues.get(position).getText());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public Date getTwitterDate(String date) throws ParseException {

        final String twitterDate="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterDate, Locale.US);
        sf.setLenient(true);

        return sf.parse(date);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public final TextView mUserScreenname;
        public final TextView mMessage;
        public final TextView mMessageDate;

        public TwitterTweet mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mUserScreenname = (TextView) view.findViewById(R.id.twitter_user_screenname);
            mMessage = (TextView) view.findViewById(R.id.tweet_messsage);
            mMessageDate = (TextView) view.findViewById(R.id.tweet_message_date);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMessage.getText() + "'";
        }
    }
}
