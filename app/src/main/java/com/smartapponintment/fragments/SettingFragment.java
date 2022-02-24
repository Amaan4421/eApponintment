package com.smartapponintment.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartapponintment.R;
import com.smartapponintment.activities.LoginActivity;
import com.smartapponintment.activities.MainActivity;


public class SettingFragment extends Fragment {
    @Nullable
    @Override




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_setting,container,false);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("KEY_PREF_EMAIL");
        editor.remove("KEY_PREF_Password");
        editor.commit();


        return rootview;
    }
}