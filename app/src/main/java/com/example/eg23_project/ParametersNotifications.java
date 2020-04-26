package com.example.eg23_project;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class ParametersNotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters_notifications);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Notifications");

        actionBar.setDisplayHomeAsUpEnabled(true);

        Switch activate_notif = findViewById(R.id.activate_notif);
        activate_notif.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    findViewById(R.id.alerts).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.alerts).setVisibility(View.GONE);
                }
            }
        });

    }
}
