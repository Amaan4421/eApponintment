package com.smartapponintment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientDetailsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CircleImageView imageView;
    TextView tvPname,tvDname,tvHospname,tvAdate,tvAtime,tvPnum;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Appointment");

        imageView = findViewById(R.id.img_book);
        tvPname = findViewById(R.id.tv_doc1);
        tvDname = findViewById(R.id.tv_doc2);
        tvHospname = findViewById(R.id.tv_doc3);
        tvAtime = findViewById(R.id.tv_doc4);
        tvAdate = findViewById(R.id.tv_doc5);
        tvPnum = findViewById(R.id.tv_doc6);
        toolbar = findViewById(R.id.toolbar);

        Intent i = getIntent();
        String strId = i.getStringExtra("S_ID");
        String strPname = i.getStringExtra("S_PNMAE");
        String strDname = i.getStringExtra("S_DNAME");
        String strHospname = i.getStringExtra("S_HOSPNAME");
        String strPnum = i.getStringExtra("S_PNUM");
        String strAdate = i.getStringExtra("S_ADATE");
        String strAtime = i.getStringExtra("S_ATIME");

        tvPname.setText(strPname);
        tvDname.setText(strDname);
        tvHospname.setText(strHospname);
        tvAtime.setText(strAtime);
        tvAdate.setText(strAdate);
        tvPnum.setText(strPnum);
    }
}