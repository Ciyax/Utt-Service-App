package com.example.eg23_project;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView cityText;
    private TextView condDescr;
    private Button weatherButton;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private TextView hum;
    private ImageView imgView;
    private String url_img;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((MainActivity) getActivity()).setTitleAppBar(R.string.app_name);

        // Génération de la liste d'évènements à venir sur l'écran d'accueil
        Fragment fragment = new EventItemFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

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

        // Récupération des CardView qui nous serviront de boutons
        CardView planning = view.findViewById(R.id.discover_calendar);
        CardView contacts = view.findViewById(R.id.discover_contacts);
        CardView prop_ue = view.findViewById(R.id.discover_prop);
        CardView simulation = view.findViewById(R.id.discover_simulation);
        CardView inf_pratiques = view.findViewById(R.id.discover_infos);
        CardView messenger = view.findViewById(R.id.discover_messenger);

        // Récupération du bouton pour accéder directement au planning
        ImageButton calendarButton = (ImageButton) view.findViewById(R.id.goto_calendar);
        // variables vue qui accueilleront le résulat de la requête
        cityText = (TextView) view.findViewById(R.id.cityText);
        condDescr = (TextView) view.findViewById(R.id.condDescr);
        temp = (TextView) view.findViewById(R.id.temp);
        hum = (TextView) view.findViewById(R.id.hum);
        press = (TextView) view.findViewById(R.id.press);
        windSpeed = (TextView) view.findViewById(R.id.windSpeed);
        windDeg = (TextView) view.findViewById(R.id.windDeg);
        imgView = (ImageView) view.findViewById(R.id.condIcon);
        weatherButton = (Button) view.findViewById(R.id.weatherButton);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue
                RequestQueue queue = Volley.newRequestQueue(v.getContext());
                // Request a json object from the provided URL.
                String urlpotter = "http://api.openweathermap.org/data/2.5/weather?q=Troyes&APPID=0d9ccc12c5de785267f2f0a57d073279";
                JsonObjectRequest weatherRequest = new JsonObjectRequest
                        (Request.Method.GET,(urlpotter), null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String city = "Troyes,FR";
                                    cityText.setText(city);
                                    JSONArray array = response.getJSONArray("weather");
                                    JSONObject weather = array.getJSONObject(0);
                                    String description = weather.getString("main");
                                    condDescr.setText(description);

                                    JSONObject main = response.getJSONObject("main");
                                    String temperature = main.getString("temp");
                                    float temper = Float.parseFloat(temperature);
                                    String pressure = main.getString("pressure");
                                    String humidity = main.getString("humidity");
                                    temp.setText("" + Math.round(temper - 273.15) + "°C");
                                    hum.setText("" + humidity + "%");
                                    press.setText("" + pressure + " hPa");

                                    JSONObject wind = response.getJSONObject("wind");
                                    String speed = wind.getString("speed");
                                    String deg = wind.getString("deg");
                                    windSpeed.setText("" + speed + " mps");
                                    windDeg.setText("," + deg + " Deg");
                                    String img_icon = weather.getString("icon");
                                    url_img = (IMG_URL+img_icon+"@2x.png");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    windDeg.setText("Something got wrong with the try catch");
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                temp.setText("That didn't work !" + error.getMessage());
                            }
                        });
                ImageRequest pictureRequest = new ImageRequest(url_img, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imgView.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.ARGB_4444, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        imgView.setImageResource(R.drawable.icon_default_weather);
                    }
                });
                queue.add(weatherRequest);
                queue.add(pictureRequest);
            }
        });

        // Association d'une action à sa CardView
        inf_pratiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.nav_practical_inf;
                Fragment fragment = new PracticalInf();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, fragment);
                ft.commit();

                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    if(navigationView.getMenu().getItem(i).getItemId() == id) {
                        navigationView.getMenu().getItem(i).setChecked(true);
                    } else {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                }
            }
        });

        planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.nav_calendar;
                Fragment fragment = new Calendar();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, fragment);
                ft.commit();

                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    if(navigationView.getMenu().getItem(i).getItemId() == id) {
                        navigationView.getMenu().getItem(i).setChecked(true);
                    } else {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                }
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.nav_contacts;
                Fragment fragment = new Contacts();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, fragment);
                ft.commit();

                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    if(navigationView.getMenu().getItem(i).getItemId() == id) {
                        navigationView.getMenu().getItem(i).setChecked(true);
                    } else {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                }
            }
        });

        prop_ue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.nav_prop_ue;
                Fragment fragment = new PropUe();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, fragment);
                ft.commit();

                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    if(navigationView.getMenu().getItem(i).getItemId() == id) {
                        navigationView.getMenu().getItem(i).setChecked(true);
                    } else {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                }
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.nav_calendar;
                Fragment fragment = new Calendar();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, fragment);
                ft.commit();

                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    if(navigationView.getMenu().getItem(i).getItemId() == id) {
                        navigationView.getMenu().getItem(i).setChecked(true);
                    } else {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                }
            }
        });

        simulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.nav_end_cursus;
                Fragment fragment = new SimuEndCursus();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, fragment);
                ft.commit();

                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    if(navigationView.getMenu().getItem(i).getItemId() == id) {
                        navigationView.getMenu().getItem(i).setChecked(true);
                    } else {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                }
            }
        });

        messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.nav_messenger;
                Fragment fragment = new MessengerContacts();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_content, fragment);
                ft.commit();

                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    if(navigationView.getMenu().getItem(i).getItemId() == id) {
                        navigationView.getMenu().getItem(i).setChecked(true);
                    } else {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                }
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
}
