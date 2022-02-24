package com.smartapponintment.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.smartapponintment.R;
import com.smartapponintment.databinding.ActivityBottomNavBinding;
import com.smartapponintment.fragments.HomeFragment;
import com.smartapponintment.fragments.ProfileFragment;
import com.smartapponintment.fragments.SettingFragment;

public class BottomNavActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        bottomNavigationView = findViewById(R.id.bottom_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (id == R.id.nav_home) {

                    fragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");

                } else if (id == R.id.nav_book) {

                    toolbar.setTitle("Book Appointment");
//                    fragment = new GalleryFragment();
//                    fragmentTransaction.replace(R.id.frame, fragment);
//                    fragmentTransaction.commit();

                } else if (id == R.id.nav_profile) {

                    fragment = new ProfileFragment();
                    toolbar.setTitle("Profile");
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();


                } else if (id == R.id.nav_setting) {

                    toolbar.setTitle("Setting");
                    fragment = new SettingFragment();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                }
                    return true;
                }

        });
    }
}

