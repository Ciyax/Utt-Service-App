package com.example.eg23_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.PopupMenu;

import com.example.eg23_project.dummy.Contact;
import com.example.eg23_project.dummy.Conversation;
import com.example.eg23_project.dummy.Event;
import com.example.eg23_project.dummy.Ue;
import com.example.eg23_project.ui.login.LoginActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        EventItemFragment.OnListFragmentInteractionListener,
        ContactItemFragment.OnListFragmentInteractionListener,
        UeItemBrancheAutomneFragment.OnListFragmentInteractionListener,
        UeItemFiliereAutomneFragment.OnListFragmentInteractionListener,
        MessengerListConversationFragment.OnListFragmentInteractionListener {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new Home();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content, fragment);
        ft.commit();

        // Changement de la langue du calendrier
        String languageToLoad = "fr";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if ((fragment instanceof Home) && (menu != null)) {
            menu.findItem(R.id.home_button).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.home_button) {
            Fragment fragment = new Home();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content, fragment);
            ft.commit();

            NavigationView navigationView = findViewById(R.id.nav_view);
            int size = navigationView.getMenu().size();
            for (int i = 0; i < size; i++) {
                navigationView.getMenu().getItem(i).setChecked(false);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_calendar:
                fragment = new Calendar();
                break;
            case R.id.nav_contacts:
                fragment = new Contacts();
                break;
            case R.id.nav_prop_ue:
                fragment = new PropUe();
                break;
            case R.id.nav_end_cursus:
                fragment = new SimuEndCursus();
                break;
            case R.id.nav_practical_inf:
                fragment = new PracticalInf();
                break;
            case R.id.nav_messenger:
                fragment = new MessengerContacts();
                break;
            case R.id.nav_notifications:
                startActivity(new Intent(MainActivity.this, ParametersNotifications.class));
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
        }

        if(fragment != null) {
            NavigationView navigationView = findViewById(R.id.nav_view);
            int size = navigationView.getMenu().size();
            for (int i = 0; i < size; i++) {
                if((navigationView.getMenu().getItem(i).getItemId() == id) && (navigationView.getMenu().getItem(i).getItemId() != R.id.nav_notifications)) {
                    navigationView.getMenu().getItem(i).setChecked(true);
                } else {
                    navigationView.getMenu().getItem(i).setChecked(false);
                }
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setTitleAppBar(String title) {
        setTitle(title);
    }
    public void setTitleAppBar(int title) {
        setTitle(title);
    }

    @Override
    public void onListFragmentInteraction(Event item) {

    }

    @Override
    public void onListFragmentInteraction(Contact item) {

    }

    @Override
    public void onListFragmentInteractionFiliereAutomne(Ue item) {

    }

    @Override
    public void onListFragmentInteractionBrancheAutomne(Ue item) {

    }

    @Override
    public void onListFragmentInteraction(Conversation item) {

    }
}
