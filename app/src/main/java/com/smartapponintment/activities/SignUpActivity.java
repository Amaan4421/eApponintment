package com.smartapponintment.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.smartapponintment.R;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignup2;
    EditText edtFname;
    EditText edtMail2;
    EditText edtPassword;
    EditText edtNumber;
    TextView oldAcc;
    EditText edtPassword2;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    RadioGroup radioGroup;
    RadioButton edtB1;
    RadioButton edtB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignup2=findViewById(R.id.btn_signup2);
        edtMail2=findViewById(R.id.edt_mail2);
        edtFname=findViewById(R.id.edt_fname);
        edtPassword=findViewById(R.id.edt_password);
        edtNumber=findViewById(R.id.edt_phone2);
        edtPassword2=findViewById(R.id.edt_password2);
        oldAcc=findViewById(R.id.old_acc);
        radioGroup =  findViewById(R.id.tv_rg);
        edtB1 = findViewById(R.id.rb1);
        edtB2 = findViewById(R.id.rb2);

        btnSignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtMail2.getText().toString();
                String strPassword = edtPassword.getText().toString();
                String strPassword2 = edtPassword2.getText().toString();
                String strFname = edtFname.getText().toString();
                String strNum = edtNumber.getText().toString();
                String strB1 = edtB1.getText().toString();
                String strB2 = edtB2.getText().toString();

                if (!edtB1.isChecked() && !edtB2.isChecked()) {
                    Toast.makeText(SignUpActivity.this, "Select Doctor or Patient", Toast.LENGTH_SHORT).show();
                }
                else if(strFname.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                }
                else if(strEmail.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                }
                else if (!strEmail.matches(emailPattern)) {
                    Toast.makeText(SignUpActivity.this, "Enter valid Email id", Toast.LENGTH_SHORT).show();
                }
                else if(strNum.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if (strPassword.equals(""))
                {
                            Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (strPassword.length()<8)
                {
                    Toast.makeText(SignUpActivity.this, "Password must be have 8 characters or number", Toast.LENGTH_SHORT).show();
                }
                else if(strPassword2.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Confirm your password", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KEY_PREF_EMAIL",strEmail);
                    editor.putString("KEY_PREF_USERNAME",strFname);
                    editor.putString("KEY_PREF_MOBILE",strNum);
                    editor.putString("KEY_PREF_Password",strPassword);
                    editor.commit();

                    if(!strPassword.equals(strPassword2)){
                    Toast.makeText(SignUpActivity.this, "Check your Password", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SignUpActivity.this, "Sign Up successful!!", Toast.LENGTH_SHORT).show();
                        if(edtB2.isChecked()) {
                            Intent i = new Intent(SignUpActivity.this, BottomNavActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Intent i = new Intent(SignUpActivity.this, BottomDocActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }

            }
        });
        oldAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent (SignUpActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void onBackPressed ()
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("eAppointment");
        builder.setIcon(R.drawable.applogo);
        builder.setMessage("Are you sure, you want to Back?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
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