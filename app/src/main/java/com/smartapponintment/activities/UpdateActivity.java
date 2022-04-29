package com.smartapponintment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;
import com.smartapponintment.models.DoctorModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CircleImageView imageView;
    TextView tvName,tvEmail,tvDegree;
    EditText edtSpeciality,edtHospname,edtNum;
    Button btnUpdate;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Doctor");

        imageView = findViewById(R.id.img_book);
        toolbar = findViewById(R.id.toolbar);
        tvName = findViewById(R.id.tv_doc1);
        edtHospname = findViewById(R.id.tv_doc2);
        edtSpeciality = findViewById(R.id.tv_doc3);
        tvDegree = findViewById(R.id.tv_doc4);
        tvEmail = findViewById(R.id.tv_doc5);
        edtNum = findViewById(R.id.tv_doc6);
        btnUpdate = findViewById(R.id.btn_update);

        Intent i = getIntent();
        String strId = i.getStringExtra("KEY_ID");
        String strName = i.getStringExtra("KEY_NAME");
        String strHospname  = i.getStringExtra("KEY_DOCHOSPITAL");
        String strSpeciality = i.getStringExtra("KEY_DOCSP");
        String strDegree = i.getStringExtra("KEY_DOCDEGREE");
        String strEmail = i.getStringExtra("KEY_DOCEMAIL");
        String strNum = i.getStringExtra("KEY_DOCNUMBER");

        tvName.setText(strName);
        edtHospname.setText(strHospname);
        edtSpeciality.setText(strSpeciality);
        tvDegree.setText(strDegree);
        tvEmail.setText(strEmail);
        edtNum.setText(strNum);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(UpdateActivity.this,"Data Updated!!!",Toast.LENGTH_SHORT).show();

                String strUhospname = edtHospname.getText().toString();
                String strUspeciality = edtSpeciality.getText().toString();
                String strUnum = edtNum.getText().toString();
                DoctorModel doctorModel = new DoctorModel();
                doctorModel.setDoc_id(strId);
                doctorModel.setDoc_Name(strName);
                doctorModel.setDoc_Degree(strDegree);
                doctorModel.setDoc_Email(strEmail);
                doctorModel.setDoc_Hosp_name(strUhospname);
                doctorModel.setDoc_Speciality(strUspeciality);
                doctorModel.setDoc_Number(strUnum);
                databaseReference.child(strId).setValue(doctorModel);
                edtHospname.setText("");
                edtSpeciality.setText("");
                edtNum.setText("");

                finish();
                }
        });
    }
}