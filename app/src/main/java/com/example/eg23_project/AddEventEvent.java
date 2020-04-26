package com.example.eg23_project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEventEvent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddEventEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEventEvent extends Fragment implements DatePickerDialog.OnDateSetListener,
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

    boolean allDayLong = false;
    String dateTimeEntrySelected;

    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    public AddEventEvent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEventEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEventEvent newInstance(String param1, String param2) {
        AddEventEvent fragment = new AddEventEvent();
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
        View view = inflater.inflate(R.layout.fragment_add_event_event, container, false);

        final Switch allDayLongSwitch = (Switch) view.findViewById(R.id.all_day_long_switch);
        final TextView fromHeader = (TextView) view.findViewById(R.id.from_header);
        final TextView toHeader = (TextView) view.findViewById(R.id.to_header);
        final LinearLayout toLayout = (LinearLayout) view.findViewById(R.id.to_layout);
        dateTimeFrom = (EditText) view.findViewById(R.id.date_time_from);
        dateTimeTo = (EditText) view.findViewById(R.id.date_time_to);

        dateTimeFrom.setInputType(InputType.TYPE_NULL);
        dateTimeTo.setInputType(InputType.TYPE_NULL);

        allDayLongSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    toLayout.setVisibility(View.GONE);
                    fromHeader.setText("Quand");

                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3f);

                    float scale = getContext().getResources().getDisplayMetrics().density;
                    int px = (int) (16 * scale + 0.5f);
                    lp.setMargins(px, px, px, px);

                    fromHeader.setLayoutParams(lp);
                    fromHeader.setGravity(10);

                    dateTimeFrom.setText("");
                    dateTimeTo.setText("");
                    allDayLong = true;
                } else {
                    toLayout.setVisibility(View.VISIBLE);
                    fromHeader.setText("De");
                    fromHeader.setLayoutParams(toHeader.getLayoutParams());
                    dateTimeFrom.setText("");
                    dateTimeTo.setText("");
                    allDayLong = false;
                }
            }
        });

        dateTimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar c = java.util.Calendar.getInstance();
                year = c.get(java.util.Calendar.YEAR);
                month = c.get(java.util.Calendar.MONTH);
                day = c.get(java.util.Calendar.DAY_OF_MONTH);

                dateTimeEntrySelected = "From";

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AddEventEvent.this, year, month, day);
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AddEventEvent.this, year, month, day);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // Si l'on a sélectionné l'option "toute la journée" alors, il s'agit d'un évènement
        // s'étalant sur une journée complète seule, inutile de préciser l'heure et la date de fin
        if (!allDayLong) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), AddEventEvent.this, hour, minute, DateFormat.is24HourFormat(getActivity()));
            timePickerDialog.show();
        } else {
            // Récupération de la date
            LocalDate localDate = LocalDate.of(yearFinal, monthFinal, dayFinal);
            String dWk = localDate.format(DateTimeFormatter.ofPattern("EE", Locale.FRENCH));
            String dMth = localDate.format(DateTimeFormatter.ofPattern("dd", Locale.FRENCH));
            String mth = localDate.format(DateTimeFormatter.ofPattern("MMM", Locale.FRENCH));
            String yr = localDate.format(DateTimeFormatter.ofPattern("yyyy", Locale.FRENCH));

            // Mise en majuscule des premières lettres
            dWk = dWk.substring(0, 1).toUpperCase() + dWk.substring(1);
            mth = mth.substring(0, 1).toUpperCase() + mth.substring(1);

            // Concaténation finale de la date sélectionnée
            String dateFinal = dWk + " " + dMth + " " + mth + " " + yr;

            if (dateTimeEntrySelected.equalsIgnoreCase("From")) {
                dateTimeFrom.setText(dateFinal);
            }
        }
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
