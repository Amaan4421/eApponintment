package com.smartapponintment.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;

public class LoginActivity extends AppCompatActivity {

    Button edtLogin;
    EditText edtEmail;
    EditText edtPassword;
    TextView newAcc;
    TextView tvfp;
    RadioGroup radioGroup;
    RadioButton edtB1;
    RadioButton edtB2;
    RadioButton edtB3;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String emailAdmin = "admin27@gmail.com";
    String passAdmin = "admin273";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference=firebaseDatabase.getReference("User");


        edtLogin = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.edt_email2);
        edtPassword = findViewById(R.id.edt_password2);
        newAcc = findViewById(R.id.new_acc);
        tvfp = findViewById(R.id.tv_fp);
        radioGroup =  findViewById(R.id.tv_rg);
        edtB1 = findViewById(R.id.rb1);
        edtB2 = findViewById(R.id.rb2);
        edtB3 = findViewById(R.id.rb3);

        tvfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View fpView = layoutInflater.inflate(R.layout.raw_fp, null);
                EditText edtFPEmail = fpView.findViewById(R.id.edt_email);
                Button btnSubmit = fpView.findViewById(R.id.btn_submit);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(fpView);
                alertDialog.show();

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(LoginActivity.this, "Submit", Toast.LENGTH_SHORT).show();

                        if (alertDialog.isShowing()) {

                            alertDialog.dismiss();
                        }
                    }
                });
            }
        });


        edtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();
                String strB1 = edtB1.getText().toString();
                String strB2 = edtB2.getText().toString();
                String strB3 = edtB3.getText().toString();

                if (!edtB1.isChecked() && !edtB2.isChecked() && !edtB3.isChecked())
                {
                    Toast.makeText(LoginActivity.this, "Select Doctor or Patient or Admin", Toast.LENGTH_SHORT).show();
                }
                else if (strEmail.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                }
                else if (!strEmail.matches(emailPattern))
                {
                    Toast.makeText(LoginActivity.this, "Enter valid Email id", Toast.LENGTH_SHORT).show();
                }
                else if (strPassword.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (strPassword.length()<8)
                {
                    Toast.makeText(LoginActivity.this, "Password must be have 8 characters or number", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KEY_PREF_EMAIL",strEmail);
                    editor.putString("KEY_PREF_Password",strPassword);
                    editor.commit();

                  databaseReference.setValue("Hello Amaan");


                    if(edtB3.isChecked()) {
                        Intent i = new Intent(LoginActivity.this, BottomNavActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else if(edtB2.isChecked())
                    {
                        Intent i = new Intent(LoginActivity.this, BottomDocActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        if(strEmail.equals(emailAdmin) && strPassword.equals(passAdmin))
                        {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, BottomAdminActivity.class);
                             startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Enter Valid email or password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


                newAcc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }

            public void onBackPressed() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("eAppointment");
                builder.setIcon(R.drawable.applogo);
                builder.setMessage("Are you sure, you want to Back?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
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
        }


