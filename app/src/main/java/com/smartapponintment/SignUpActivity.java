package com.smartapponintment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignup2;
    EditText edtFname;
    EditText edtMail2;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignup2=findViewById(R.id.btn_signup2);
        edtMail2=findViewById(R.id.edt_mail2);
        edtFname=findViewById(R.id.edt_fname);
        edtPassword=findViewById(R.id.edt_password);

        btnSignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtMail2.getText().toString();
                String strPassword = edtPassword.getText().toString();
                String strFname = edtFname.getText().toString();
                if(strEmail.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                }
                else if(strFname.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                }

                else if (strPassword.equals(""))
                {
                            Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Sign Up successful!!", Toast.LENGTH_SHORT).show();
                }

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