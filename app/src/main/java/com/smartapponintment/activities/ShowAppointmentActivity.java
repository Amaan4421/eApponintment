package com.smartapponintment.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartapponintment.R;
import com.smartapponintment.adapters.AppointmentAdapter;
import com.smartapponintment.models.AppointmentModel;

import java.util.ArrayList;

public class ShowAppointmentActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<AppointmentModel> appointmentModelArrayList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    AppointmentAdapter appointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointment);

        listView = findViewById(R.id.list_view);
        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Appointment");
        appointmentModelArrayList = new ArrayList<AppointmentModel>();
        SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment",MODE_PRIVATE);

        String strId = sharedPreferences.getString("KEY_USERID","");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    AppointmentModel appointmentModel = dataSnapshot.getValue(AppointmentModel.class);
                    String pId = appointmentModel.getP_Id();
                    if (strId.equals(pId))
                    {
                        String aId = appointmentModel.getA_Id();
                        String d_name = appointmentModel.getDoc_Name();
                        String p_name = appointmentModel.getP_Name();
                        String d_number = appointmentModel.getDoc_Num();
                        String p_number = appointmentModel.getP_Num();
                        String h_name = appointmentModel.getDoc_Hospname();
                        String p_date = appointmentModel.getP_Date();
                        String p_time = appointmentModel.getP_Time();
                        AppointmentModel appointmentModel1 = new AppointmentModel();
                        appointmentModel1.setA_Id(aId);
                        appointmentModel1.setDoc_Num(d_number);
                        appointmentModel1.setP_Name(p_name);
                        appointmentModel1.setDoc_Name(d_name);
                        appointmentModel1.setP_Num(p_number);
                        appointmentModel1.setDoc_Hospname(h_name);
                        appointmentModel1.setP_Date(p_date);
                        appointmentModel1.setP_Time(p_time);
                        appointmentModelArrayList.add(appointmentModel1);
                    }
                }
                appointmentAdapter = new AppointmentAdapter(ShowAppointmentActivity.this, appointmentModelArrayList);
                if (appointmentAdapter.isEmpty())
                {
                    Toast.makeText(ShowAppointmentActivity.this, "You don't have any appointments!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    listView.setAdapter(appointmentAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}