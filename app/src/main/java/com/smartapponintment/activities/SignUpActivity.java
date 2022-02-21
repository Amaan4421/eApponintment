package com.smartapponintment.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartapponintment.R;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignup2;
    EditText edtFname;
    EditText edtMail2;
    EditText edtPassword;
    EditText edtNumber;
    TextView oldAcc;
    EditText edtPassword2;

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

        btnSignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtMail2.getText().toString();
                String strPassword = edtPassword.getText().toString();
                String strPassword2 = edtPassword2.getText().toString();
                String strFname = edtFname.getText().toString();
                String strNum = edtNumber.getText().toString();
                if(strFname.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                }
                else if(strEmail.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                }
                else if(strNum.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if (strPassword.equals(""))
                {
                            Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(strPassword2.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Password again", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Sign Up successful!!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUpActivity.this,BottomNavActivity.class);
                    startActivity(i);
                    finish();
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
        builder.setIcon(R.drawable.ic_home2);
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