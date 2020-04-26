package com.example.eg23_project;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SimuEndCursus.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SimuEndCursus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimuEndCursus extends Fragment implements PopupMenu.OnMenuItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SimuEndCursus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimuEndCursus.
     */
    // TODO: Rename and change types and number of parameters
    public static SimuEndCursus newInstance(String param1, String param2) {
        SimuEndCursus fragment = new SimuEndCursus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simulation_end_cursus, container, false);
        ImageButton moreOption = (ImageButton) view.findViewById(R.id.more_option);

        moreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(SimuEndCursus.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
        Fragment creditsPeriod = new ResultsA18();
        FragmentTransaction ft_creditsPeriod = getFragmentManager().beginTransaction();
        ft_creditsPeriod.replace(R.id.credits_period_content, creditsPeriod);
        ft_creditsPeriod.commit();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.branche_period_menu_1:
                // Mise à jour du titre et du sous-titre
                ((TextView) getView().findViewById(R.id.credits_period_header)).setText(getResources().getString(R.string.credits_header_a18));
                ((TextView) getView().findViewById(R.id.credits_subheader)).setText(getResources().getString(R.string.branche_period_i1));

                // Mise à jour du fragment des résultats
                fragment = new ResultsA18();

                break;
            case R.id.branche_period_menu_2:
                // Mise à jour du titre et du sous-titre
                ((TextView) getView().findViewById(R.id.credits_period_header)).setText(getResources().getString(R.string.credits_header_p19));
                ((TextView) getView().findViewById(R.id.credits_subheader)).setText(getResources().getString(R.string.branche_period_i2));

                // Mise à jour du fragment des résultats
                fragment = new ResultsP19();

                break;
        }

        // Chargement des résultats du semestre correspondant
        if (fragment != null) {
            FragmentTransaction ft_creditsPeriod = getFragmentManager().beginTransaction();
            ft_creditsPeriod.replace(R.id.credits_period_content, fragment);
            ft_creditsPeriod.commit();

            return true;
        } else {
            return false;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu != null) {
            menu.findItem(R.id.home_button).setVisible(true);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
