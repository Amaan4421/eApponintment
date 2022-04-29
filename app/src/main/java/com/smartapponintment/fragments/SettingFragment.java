package com.smartapponintment.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.smartapponintment.R;
import com.smartapponintment.activities.LoginActivity;

public class SettingFragment extends Fragment {

    Button btnLogout;
    TextView aboutUs;
    TextView contactUs;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_setting,container,false);
        btnLogout = rootview.findViewById(R.id.btn_logout);
        aboutUs = rootview.findViewById(R.id.au);
        contactUs = rootview.findViewById(R.id.cu);

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    LayoutInflater layoutInflater = (LayoutInflater)getLayoutInflater();
                    View tvAu = layoutInflater.inflate(R.layout.raw_au,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setView(tvAu);
                    alertDialog.show();
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater)getLayoutInflater();
                View tvCu = layoutInflater.inflate(R.layout.raw_cu,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(tvCu);
                alertDialog.show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("KEY_PREF_EMAIL");
                editor.remove("KEY_PREF_PASSWORD");
                editor.remove("KEY_USERROLE");
                editor.clear();
                editor.commit();
                Intent i = new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });
        return rootview;
    }
}