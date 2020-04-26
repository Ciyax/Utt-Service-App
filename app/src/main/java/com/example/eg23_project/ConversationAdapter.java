package com.example.eg23_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eg23_project.MessengerListConversationFragment.OnListFragmentInteractionListener;
import com.example.eg23_project.dummy.Conversation;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Conversation} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    private final List<Conversation> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ConversationAdapter(List<Conversation> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_conversation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mShortNameView.setText(mValues.get(position).getshortName());
        holder.mFullNameView.setText(mValues.get(position).getFullName());

        String subInf = mValues.get(position).getLastMessage();
        if (mValues.get(position).getIsLastMessageSenderUser()) {
            subInf = "Vous : " + mValues.get(position).getLastMessage();
        }
        holder.mLastMessageView.setText(subInf);
        holder.mLastMessageDateView.setText(mValues.get(position).getLastMessageDate());

        if (!mValues.get(position).getIsOnline()) {
            holder.mOnlineIndicatorView.setImageResource(R.drawable.circle_shape_offline_10dp);
        }

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mShortNameView;
        public final TextView mFullNameView;
        public final TextView mLastMessageView;
        public final TextView mLastMessageDateView;
        public final ImageView mOnlineIndicatorView;
        public Conversation mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mShortNameView = (TextView) view.findViewById(R.id.avatar_shortname);
            mFullNameView = (TextView) view.findViewById(R.id.item_fullname);
            mLastMessageView = (TextView) view.findViewById(R.id.item_last_msg);
            mLastMessageDateView = (TextView) view.findViewById(R.id.item_last_msg_date);
            mOnlineIndicatorView = (ImageView) view.findViewById(R.id.avatar_online_indicator);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mFullNameView.getText() + "'";
        }
    }
}
