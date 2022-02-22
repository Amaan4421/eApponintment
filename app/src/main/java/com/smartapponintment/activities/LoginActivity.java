package com.smartapponintment.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogin = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.edt_email2);
        edtPassword = findViewById(R.id.edt_password2);
        newAcc = findViewById(R.id.new_acc);
        tvfp = findViewById(R.id.tv_fp);
        radioGroup =  findViewById(R.id.tv_rg);
        edtB1 = findViewById(R.id.rb1);
        edtB2 = findViewById(R.id.rb2);

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

                if (!edtB1.isChecked() && !edtB2.isChecked()) {
                    Toast.makeText(LoginActivity.this, "Select Doctor or Patient", Toast.LENGTH_SHORT).show();
                } else if (strEmail.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                } else if (strPassword.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, BottomNavActivity.class);
                    startActivity(i);
                    finish();
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
                builder.setIcon(R.drawable.ic_home2);
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


