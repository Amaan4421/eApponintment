package com.smartapponintment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeleteActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CircleImageView imageView;
    TextView tvName,tvEmail,tvSpeciality,tvHospname,tvNum,tvDegree;
    Button btnDelete;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Doctor");

        imageView = findViewById(R.id.img_book);
        toolbar = findViewById(R.id.toolbar);
        tvName = findViewById(R.id.tv_doc1);
        tvHospname = findViewById(R.id.tv_doc2);
        tvSpeciality = findViewById(R.id.tv_doc3);
        tvDegree = findViewById(R.id.tv_doc4);
        tvEmail = findViewById(R.id.tv_doc5);
        tvNum = findViewById(R.id.tv_doc6);
        btnDelete = findViewById(R.id.btn_delete);

        Intent i = getIntent();
        String strId = i.getStringExtra("KEY_ID");
        String strName = i.getStringExtra("KEY_NAME");
        String strHospname  = i.getStringExtra("KEY_DOCHOSPITAL");
        String strSpeciality = i.getStringExtra("KEY_DOCSP");
        String strDegree = i.getStringExtra("KEY_DOCDEGREE");
        String strEmail = i.getStringExtra("KEY_DOCEMAIL");
        String strNum = i.getStringExtra("KEY_DOCNUMBER");

        tvName.setText(strName);
        tvHospname.setText(strHospname);
        tvSpeciality.setText(strSpeciality);
        tvDegree.setText(strDegree);
        tvEmail.setText(strEmail);
        tvNum.setText(strNum);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(DeleteActivity.this,"Doctor Deleted Successfully!!!",Toast.LENGTH_SHORT).show();
                databaseReference.child(strId).removeValue();

                Intent i = new Intent(DeleteActivity.this,BottomAdminActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}