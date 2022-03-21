package com.smartapponintment.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartapponintment.R;
import com.smartapponintment.fragments.HomeDocFragment;
import com.smartapponintment.fragments.ProfileFragment;
import com.smartapponintment.fragments.SettingFragment;

public class BottomDocActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_doc);
        bottomNavigationView = findViewById(R.id.bottom_docview);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new HomeDocFragment())
                .commit();
        toolbar.setTitle("Home");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


                if (id == R.id.doc_home) {

                    fragment = new HomeDocFragment();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");

                }
                else if (id == R.id.doc_schedule) {

                    toolbar.setTitle("Schedule");
//                    fragment = new GalleryFragment();
//                    fragmentTransaction.replace(R.id.frame, fragment);
//                    fragmentTransaction.commit();

                } else if (id == R.id.doc_profile) {

                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();


                } else if (id == R.id.doc_settings) {

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