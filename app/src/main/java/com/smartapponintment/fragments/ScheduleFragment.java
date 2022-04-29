package com.smartapponintment.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartapponintment.R;
import com.smartapponintment.adapters.ScheduleAdapter;
import com.smartapponintment.models.AppointmentModel;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    private ListView listView;
    private ArrayList<AppointmentModel> appointmentModelArrayList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    ScheduleAdapter scheduleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_schedule,container,false);

        listView = rootview.findViewById(R.id.list_view);
        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Appointment");
        appointmentModelArrayList = new ArrayList<AppointmentModel>();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);

        String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL","");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    AppointmentModel appointmentModel = dataSnapshot.getValue(AppointmentModel.class);
                    String dEmail = appointmentModel.getDoc_Email();
                    if (strEmail.equals(dEmail))
                    {
                        String pName = appointmentModel.getP_Name();
                        String pNum = appointmentModel.getP_Num();
                        String aDate = appointmentModel.getP_Date();
                        String aTime = appointmentModel.getP_Time();
                        String dName = appointmentModel.getDoc_Name();
                        String dHospname = appointmentModel.getDoc_Hospname();
                        AppointmentModel appointmentModel2 = new AppointmentModel();
                        appointmentModel2.setP_Name(pName);
                        appointmentModel2.setP_Num(pNum);
                        appointmentModel2.setP_Date(aDate);
                        appointmentModel2.setP_Time(aTime);
                        appointmentModel2.setDoc_Name(dName);
                        appointmentModel2.setDoc_Hospname(dHospname);
                        appointmentModelArrayList.add(appointmentModel2);
                    }
                    scheduleAdapter = new ScheduleAdapter(getActivity(), appointmentModelArrayList);
                    if(scheduleAdapter.isEmpty())
                    {
                        Toast.makeText(getActivity(), "You don't have any appointments!!!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        listView.setAdapter(scheduleAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return rootview;
    }
}