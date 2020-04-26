package com.example.eg23_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eg23_project.UeItemFiliereAutomneFragment.OnListFragmentInteractionListener;
import com.example.eg23_project.dummy.Ue;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Ue} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class UeItemFiliereAutomneAdapter extends RecyclerView.Adapter<UeItemFiliereAutomneAdapter.ViewHolder> {

    private final List<Ue> mValues;
    private final OnListFragmentInteractionListener mListener;

    public UeItemFiliereAutomneAdapter(List<Ue> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ueitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTypeView.setText(mValues.get(position).getType());
        holder.mLabelView.setText(mValues.get(position).getLabel());

        String subInf = mValues.get(position).getCredit() + " Cred.";
        holder.mCreditsView.setText(subInf);
        holder.mDescriptionView.setText(mValues.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteractionFiliereAutomne(holder.mItem);
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
        public final TextView mTypeView;
        public final TextView mLabelView;
        public final TextView mCreditsView;
        public final TextView mDescriptionView;
        public Ue mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTypeView = (TextView) view.findViewById(R.id.item_type);
            mLabelView = (TextView) view.findViewById(R.id.item_label);
            mCreditsView = (TextView) view.findViewById(R.id.item_credits);
            mDescriptionView = (TextView) view.findViewById(R.id.item_description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mLabelView.getText() + "'";
        }
    }
}
