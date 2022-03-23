package com.smartapponintment.fragments;

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
import androidx.fragment.app.FragmentTransaction;

import com.smartapponintment.R;


public class AdminHomeFragment extends Fragment {

    Button adminPanel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_admin_home,container,false);
        adminPanel = rootview.findViewById(R.id.adminButton);

        adminPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater)getLayoutInflater();
                View tvAdmin = layoutInflater.inflate(R.layout.raw_admin,null);
                TextView add2 = tvAdmin.findViewById(R.id.add2);
                TextView delete2 = tvAdmin.findViewById(R.id.delete2);
                TextView hosp2 = tvAdmin.findViewById(R.id.hosp2);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(tvAdmin);
                alertDialog.show();

                add2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (alertDialog.isShowing())
                        {
                            alertDialog.dismiss();
                        }
                        Fragment fragment = null;
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragment = new AddDoctorFragment();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                    }
                });

                delete2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (alertDialog.isShowing())
                        {
                            alertDialog.dismiss();
                        }
                        Fragment fragment = null;
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragment = new DeleteDoctorFragment();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                    }
                });

                hosp2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (alertDialog.isShowing())
                        {
                            alertDialog.dismiss();
                        }
                        Fragment fragment = null;
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragment = new AddHospitalFragment();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                    }
                });

            }
        });

        return rootview;
    }
}