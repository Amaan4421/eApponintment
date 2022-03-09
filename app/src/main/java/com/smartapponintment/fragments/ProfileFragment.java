package com.smartapponintment.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smartapponintment.R;


public class ProfileFragment extends Fragment {

        Button submitChanges;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                View rootView = inflater.inflate(R.layout.fragment_profile,container,false);

                submitChanges = rootView.findViewById(R.id.save);


                SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
                String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");
                TextView tvEmail = rootView.findViewById(R.id.text);
                tvEmail.setText(strEmail);
                String strFname = sharedPreferences.getString("KEY_PREF_USERNAME", "");
                TextView tvFname = rootView.findViewById(R.id.text2_1);
                tvFname.setText(strFname);
                String strNum = sharedPreferences.getString("KEY_PREF_MOBILE", "");
                TextView tvNum = rootView.findViewById(R.id.text3_1);
                tvNum.setText(strNum);


                submitChanges.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                Toast.makeText(getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                        }
                });

                return rootView;
        }
}