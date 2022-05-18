package com.smartapponintment.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;
import com.smartapponintment.models.AppointmentModel;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookDoctorActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    CircleImageView imageView;
    ImageView imageTime,imageDate;
    TextView tvName,tvSpeciality,tvHospname,tvDegree,tvNum;
    EditText edtPname,edtPnum;
    TextView edtTime,edtDate;
    Button btnBook;
    Toolbar toolbar;
    int date, month, year;
    int aDate,aMonth,aYear;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor);

        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Appointment");

        imageView = findViewById(R.id.img_book);
        imageTime = findViewById(R.id.img_time);
        imageDate = findViewById(R.id.img_date);
        toolbar = findViewById(R.id.toolbar);
        tvName = findViewById(R.id.tv_doc1);
        tvHospname = findViewById(R.id.tv_doc2);
        tvSpeciality = findViewById(R.id.tv_doc3);
        tvDegree = findViewById(R.id.tv_doc4);
        tvNum = findViewById(R.id.tv_doc5);
        edtPname = findViewById(R.id.edt_name);
        edtPnum = findViewById(R.id.edt_num);
        edtDate = findViewById(R.id.edt_e1);
        edtTime = findViewById(R.id.edt_e2);
        btnBook = findViewById(R.id.btn_book);

        Intent i = getIntent();
        String strId = i.getStringExtra("KEY_ID");
        String strName = i.getStringExtra("KEY_NAME");
        String strHospname  = i.getStringExtra("KEY_DOCHOSPITAL");
        String strSpeciality = i.getStringExtra("KEY_DOCSP");
        String strDegree = i.getStringExtra("KEY_DOCDEGREE");
        String strNum = i.getStringExtra("KEY_DOCNUMBER");
        String strDocemail= i.getStringExtra("KEY_DOCEMAIL");

        tvName.setText(strName);
        tvHospname.setText(strHospname);
        tvSpeciality.setText(strSpeciality);
        tvDegree.setText(strDegree);
        tvNum.setText(strNum);

        Calendar calendar = Calendar.getInstance();
        date = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);

        int tDate = calendar.get(Calendar.DATE);
        int tMonth = calendar.get(Calendar.MONTH);
        int tYear = calendar.get(Calendar.YEAR);

        imageTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookDoctorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofday, int minute) {
                                edtTime.setText(hourofday+":"+minute);
                    }
                },hour,minute,false);
                timePickerDialog.show();
            }
        });

        imageDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookDoctorActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        aDate = dayOfMonth;
                        aMonth = month;
                        aYear = year;
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strPname = edtPname.getText().toString();
                String strPnum = edtPnum.getText().toString();
                String strDate = edtDate.getText().toString();
                String strTime = edtTime.getText().toString();

                if (strPname.equals(""))
                {
                    Toast.makeText(BookDoctorActivity.this, "Patient Name Required!!!", Toast.LENGTH_LONG).show();
                }
                else if (strPnum.equals(""))
                {
                    Toast.makeText(BookDoctorActivity.this, "Patient Number Required!!!", Toast.LENGTH_LONG).show();
                }
                else if (strPnum.length() < 10 || strPnum.length() > 10)
                {
                    Toast.makeText(BookDoctorActivity.this, "Number should be 10 digits only!!!", Toast.LENGTH_LONG).show();
                }
                else if (strDate.equals(""))
                {
                    Toast.makeText(BookDoctorActivity.this, "Choose Appointment Date First!!!", Toast.LENGTH_LONG).show();
                }
                else if (strTime.equals(""))
                {
                    Toast.makeText(BookDoctorActivity.this, "Choose Appointment Time First!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (aDate < tDate)
                    {
                        if(aMonth <= tMonth)
                        {
                            if(aYear <= tYear)
                            {
                                Toast.makeText(BookDoctorActivity.this, "You can't choose past dates!!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BookDoctorActivity.this);
                                builder.setTitle("eAppointment");
                                builder.setIcon(R.drawable.applogo);
                                builder.setMessage("By clicking Yes means you confirm this appointment.");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int j) {
                                        Toast.makeText(BookDoctorActivity.this, "Your appointment is booked successfully!!!", Toast.LENGTH_LONG).show();

                                        SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        String strPid = sharedPreferences.getString("KEY_USERID", "");
                                        String strUid = databaseReference.push().getKey();

                                        AppointmentModel appointmentModel = new AppointmentModel();
                                        appointmentModel.setA_Id(strUid);
                                        appointmentModel.setP_Id(strPid);
                                        appointmentModel.setP_Name(strPname);
                                        appointmentModel.setP_Num(strPnum);
                                        appointmentModel.setP_Date(strDate);
                                        appointmentModel.setP_Time(strTime);
                                        appointmentModel.setDoc_id(strId);
                                        appointmentModel.setDoc_Name(strName);
                                        appointmentModel.setDoc_Hospname(strHospname);
                                        appointmentModel.setDoc_Sp(strSpeciality);
                                        appointmentModel.setDoc_Degree(strDegree);
                                        appointmentModel.setDoc_Num(strNum);
                                        appointmentModel.setDoc_Email(strDocemail);
                                        editor.putString("A_ID", strUid);
                                        editor.putString("P_ID", strPid);
                                        editor.putString("P_NAME", strPname);
                                        editor.putString("P_NUMBER", strPnum);
                                        editor.putString("P_DATE", strDate);
                                        editor.putString("P_TIME", strTime);
                                        editor.putString("D_ID", strId);
                                        editor.putString("D_NAME", strName);
                                        editor.putString("D_HOSPNAME", strHospname);
                                        editor.putString("D_SP", strSpeciality);
                                        editor.putString("D_DEGREE", strDegree);
                                        editor.putString("D_NUM", strNum);
                                        editor.putString("D_EMAIL", strDocemail);
                                        editor.commit();
                                        databaseReference.child(strUid).setValue(appointmentModel);

                                        Intent i = new Intent(BookDoctorActivity.this, BottomNavActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                builder.show();
                            }
                        }
                    }
                    else
                    {
                        if(aMonth <= tMonth)
                        {
                            if (aYear <= tYear)
                            {
                                Toast.makeText(BookDoctorActivity.this, "You can't choose past dates!!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BookDoctorActivity.this);
                                builder.setTitle("eAppointment");
                                builder.setIcon(R.drawable.applogo);
                                builder.setMessage("By clicking Yes means you confirm this appointment.");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int j) {
                                        Toast.makeText(BookDoctorActivity.this, "Your appointment is booked successfully!!!", Toast.LENGTH_LONG).show();

                                        SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        String strPid = sharedPreferences.getString("KEY_USERID", "");
                                        String strUid = databaseReference.push().getKey();

                                        AppointmentModel appointmentModel = new AppointmentModel();
                                        appointmentModel.setA_Id(strUid);
                                        appointmentModel.setP_Id(strPid);
                                        appointmentModel.setP_Name(strPname);
                                        appointmentModel.setP_Num(strPnum);
                                        appointmentModel.setP_Date(strDate);
                                        appointmentModel.setP_Time(strTime);
                                        appointmentModel.setDoc_id(strId);
                                        appointmentModel.setDoc_Name(strName);
                                        appointmentModel.setDoc_Hospname(strHospname);
                                        appointmentModel.setDoc_Sp(strSpeciality);
                                        appointmentModel.setDoc_Degree(strDegree);
                                        appointmentModel.setDoc_Num(strNum);
                                        appointmentModel.setDoc_Email(strDocemail);
                                        editor.putString("A_ID", strUid);
                                        editor.putString("P_ID", strPid);
                                        editor.putString("P_NAME", strPname);
                                        editor.putString("P_NUMBER", strPnum);
                                        editor.putString("P_DATE", strDate);
                                        editor.putString("P_TIME", strTime);
                                        editor.putString("D_ID", strId);
                                        editor.putString("D_NAME", strName);
                                        editor.putString("D_HOSPNAME", strHospname);
                                        editor.putString("D_SP", strSpeciality);
                                        editor.putString("D_DEGREE", strDegree);
                                        editor.putString("D_NUM", strNum);
                                        editor.putString("D_EMAIL", strDocemail);
                                        editor.commit();
                                        databaseReference.child(strUid).setValue(appointmentModel);

                                        Intent i = new Intent(BookDoctorActivity.this, BottomNavActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                builder.show();
                            }
                        }
                    }
                }
            }
        });
    }
}