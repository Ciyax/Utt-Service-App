package com.example.eg23_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Calendar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Calendar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calendar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Calendar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calendar.
     */
    // TODO: Rename and change types and number of parameters
    public static Calendar newInstance(String param1, String param2) {
        Calendar fragment = new Calendar();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ((MainActivity) getActivity()).setTitleAppBar("Calendrier");

        // Génération de la liste de cours et d'évènements à venir
        Fragment courses = new EventItemFragment();
        FragmentTransaction ft_courses = getFragmentManager().beginTransaction();
        ft_courses.replace(R.id.next_courses_content, courses);
        ft_courses.commit();

        Fragment meeting = new EventItemFragment();
        FragmentTransaction ft_meeting = getFragmentManager().beginTransaction();
        ft_meeting.replace(R.id.next_meeting_content, meeting);
        ft_meeting.commit();

        Fragment event = new EventItemFragment();
        FragmentTransaction ft_event = getFragmentManager().beginTransaction();
        ft_event.replace(R.id.next_event_content, event);
        ft_event.commit();

        // Chargement des éléments pour afficher la date du jour
        TextView dateOfMonth = view.findViewById(R.id.date_of_month);
        TextView dayOfWeek = view.findViewById(R.id.day_of_week);
        TextView year = view.findViewById(R.id.year);

        // Chargement de la date du jour
        LocalDate localDate = LocalDate.now();
        String dWk = localDate.format(DateTimeFormatter.ofPattern("EE", Locale.FRENCH));
        String dMth = localDate.format(DateTimeFormatter.ofPattern("dd", Locale.FRENCH));
        String month = localDate.format(DateTimeFormatter.ofPattern("MMM", Locale.FRENCH));
        String yr = localDate.format(DateTimeFormatter.ofPattern("yyyy", Locale.FRENCH));

        // Mise en majuscule des premières lettres
        dWk = dWk.substring(0, 1).toUpperCase() + dWk.substring(1);
        month = month.substring(0, 1).toUpperCase() + month.substring(1);

        // Affichage de la date
        String dateMonth = dMth + " " + month;
        dateOfMonth.setText(dateMonth);
        dayOfWeek.setText(dWk);
        year.setText(yr);

        // Gestion du click du bouton d'ajout d'évènement
        FloatingActionButton fab = view.findViewById(R.id.add_event);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddEvent.class));
            }
        });

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
