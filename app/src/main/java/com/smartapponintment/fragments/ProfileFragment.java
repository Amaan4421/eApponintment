package com.smartapponintment.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.smartapponintment.R;

import org.w3c.dom.Text;


public class ProfileFragment extends Fragment {


        NavigationView navigationView;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
                String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");


                View headerView = navigationView.getHeaderView(0);
                TextView tvEmail = headerView.findViewById(R.id.text);
                tvEmail.setText(strEmail);




                return headerView;
        }
}