package com.example.eg23_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eg23_project.ContactItemFragment.OnListFragmentInteractionListener;
import com.example.eg23_project.dummy.Contact;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ContactItemAdapter extends RecyclerView.Adapter<ContactItemAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ContactItemAdapter(List<Contact> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        String subInf = mValues.get(position).getLastName() + " " + mValues.get(position).getFirstName();
        holder.mFullName.setText(subInf);

        subInf = mValues.get(position).getPosition() + " - " + mValues.get(position).getOffice();
        holder.mContentView.setText(subInf);

        holder.mShortName.setText(mValues.get(position).getShortName());

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
        public final TextView mFullName;
        public final TextView mContentView;
        public final TextView mShortName;
        public Contact mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mFullName = (TextView) view.findViewById(R.id.item_name);
            mContentView = (TextView) view.findViewById(R.id.item_profile);
            mShortName = (TextView) view.findViewById(R.id.avatar_text);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
