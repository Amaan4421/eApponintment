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
import com.smartapponintment.fragments.AdminHomeFragment;
import com.smartapponintment.fragments.AdminProfileFragment;

public class BottomAdminActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_admin);
        bottomNavigationView = findViewById(R.id.bottom_adview);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new AdminHomeFragment())
                .commit();
        toolbar.setTitle("Home");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (id == R.id.ad_home)
                {
                    fragment = new AdminHomeFragment();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Home");
                }
                else if (id == R.id.ad_profile)
                {
                    toolbar.setTitle("Profile");
                    fragment = new AdminProfileFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }
}