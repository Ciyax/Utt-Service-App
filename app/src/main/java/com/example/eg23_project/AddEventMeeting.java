package com.example.eg23_project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEventMeeting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddEventMeeting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEventMeeting extends Fragment implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText dateTimeFrom;
    private EditText dateTimeTo;

    String dateTimeEntrySelected;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    public AddEventMeeting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEventMeeting.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEventMeeting newInstance(String param1, String param2) {
        AddEventMeeting fragment = new AddEventMeeting();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event_meeting, container, false);

        dateTimeFrom = (EditText) view.findViewById(R.id.date_time_from);
        dateTimeTo = (EditText) view.findViewById(R.id.date_time_to);

        dateTimeFrom.setInputType(InputType.TYPE_NULL);
        dateTimeTo.setInputType(InputType.TYPE_NULL);

        dateTimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar c = java.util.Calendar.getInstance();
                year = c.get(java.util.Calendar.YEAR);
                month = c.get(java.util.Calendar.MONTH);
                day = c.get(java.util.Calendar.DAY_OF_MONTH);

                dateTimeEntrySelected = "From";

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AddEventMeeting.this, year, month, day);
                datePickerDialog.show();
            }
        });

        dateTimeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar c = java.util.Calendar.getInstance();
                year = c.get(java.util.Calendar.YEAR);
                month = c.get(java.util.Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                dateTimeEntrySelected = "To";

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AddEventMeeting.this, year, month, day);
                datePickerDialog.show();
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), AddEventMeeting.this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;

        // Récupération de la date
        LocalDate localDate = LocalDate.of(yearFinal, monthFinal, dayFinal);
        String dWk = localDate.format(DateTimeFormatter.ofPattern("EE", Locale.FRENCH));
        String dMth = localDate.format(DateTimeFormatter.ofPattern("dd", Locale.FRENCH));
        String mth = localDate.format(DateTimeFormatter.ofPattern("MMM", Locale.FRENCH));
        String yr = localDate.format(DateTimeFormatter.ofPattern("yyyy", Locale.FRENCH));

        // Mise en majuscule des premières lettres
        dWk = dWk.substring(0, 1).toUpperCase() + dWk.substring(1);
        mth = mth.substring(0, 1).toUpperCase() + mth.substring(1);

        // Normalisation de l'heure sélectionnée
        LocalTime tm = LocalTime.of(hourFinal, minuteFinal);

        // Concaténation finale de la date sélectionnée
        String dateFinal = dWk + " " + dMth + " " + mth + " " + yr + " - " + tm;

        if (dateTimeEntrySelected.equalsIgnoreCase("From")) {
            dateTimeFrom.setText(dateFinal);
        } else if (dateTimeEntrySelected.equalsIgnoreCase("To")) {
            dateTimeTo.setText(dateFinal);
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
}
