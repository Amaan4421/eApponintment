package com.smartapponintment.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CircleImageView imageView;
    TextView tvPname,tvDname,tvHospname,tvAdate,tvAtime;
    Button btnCancel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Appointment");

        imageView = findViewById(R.id.img_book);
        tvPname = findViewById(R.id.tv_doc1);
        tvDname = findViewById(R.id.tv_doc2);
        tvHospname = findViewById(R.id.tv_doc3);
        tvAtime = findViewById(R.id.tv_doc4);
        tvAdate = findViewById(R.id.tv_doc5);
        toolbar = findViewById(R.id.toolbar);
        btnCancel = findViewById(R.id.btn_cancel);

        Intent i = getIntent();
        String strId = i.getStringExtra("KEY_ID");
        String strPname = i.getStringExtra("KEY_PNAME");
        String strDname = i.getStringExtra("KEY_DOCNAME");
        String strHospname = i.getStringExtra("KEY_HNAME");
        String strAtime = i.getStringExtra("KEY_ATIME");
        String strAdate = i.getStringExtra("KEY_ADATE");

        tvPname.setText(strPname);
        tvDname.setText(strDname);
        tvHospname.setText(strHospname);
        tvAdate.setText(strAdate);
        tvAtime.setText(strAtime);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentActivity.this);
                builder.setTitle("eAppointment");
                builder.setIcon(R.drawable.applogo);
                builder.setMessage("Are you sure, you want to cancel this Appointment?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AppointmentActivity.this,"Appointment Deleted!!!",Toast.LENGTH_SHORT).show();
                        databaseReference.child(strId).removeValue();
                        Intent i = new Intent(AppointmentActivity.this,ShowAppointmentActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
}