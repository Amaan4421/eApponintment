package com.smartapponintment.activities;

import android.os.Bundle;
import android.view.MenuItem;

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

import com.smartapponintment.R;
import com.smartapponintment.databinding.ActivityBottomNavBinding;

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

//                    fragment = new HomeFragment();
//                    fragmentTransaction.replace(R.id.frame,fragment);
//                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");

                } else if (id == R.id.nav_book) {

                    toolbar.setTitle("Book Appointment");
//                    fragment = new GalleryFragment();
//                    fragmentTransaction.replace(R.id.frame, fragment);
//                    fragmentTransaction.commit();

                } else if (id == R.id.nav_profile) {

                    toolbar.setTitle("Profile");
//                    fragment = new GalleryFragment();
//                    fragmentTransaction.replace(R.id.frame,fragment);
//                    fragmentTransaction.commit();

                } else if (id == R.id.nav_setting) {

                    toolbar.setTitle("Setting");
//                    fragment = new GalleryFragment();
//                    fragmentTransaction.replace(R.id.frame,fragment);
//                    fragmentTransaction.commit();
                }
                    return true;
                }

        });
    }
}

