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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;
import com.smartapponintment.models.RegisterModel;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignup2;
    EditText edtFname;
    EditText edtMail2;
    EditText edtPassword;
    EditText edtNumber;
    TextView oldAcc;
    EditText edtPassword2;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String  passwordPattern = "[A-Za-z]+[0-9]+";
    RadioGroup radioGroup;
    RadioButton edtB1;
    RadioButton edtB2;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Register");

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

                if (!edtB1.isChecked() && !edtB2.isChecked())
                {
                    Toast.makeText(SignUpActivity.this, "Select Doctor or Patient", Toast.LENGTH_SHORT).show();
                }
                else if (strFname.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                }
                else if (strEmail.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                }
                else if (!strEmail.matches(emailPattern))
                {
                    Toast.makeText(SignUpActivity.this, "Enter valid Email id", Toast.LENGTH_SHORT).show();
                }
                else if (strNum.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if (strPassword.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                }
                else if (strPassword.length() < 8)
                {
                    Toast.makeText(SignUpActivity.this, "Password must be have 8 characters or number", Toast.LENGTH_SHORT).show();
                }
                else if (!strPassword.matches(passwordPattern))
                {
                    Toast.makeText(SignUpActivity.this, "Password must contain atleast one number", Toast.LENGTH_SHORT).show();
                }
                else if (strPassword2.equals(""))
                {
                    Toast.makeText(SignUpActivity.this, "Write your password again", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (!strPassword.equals(strPassword2))
                    {
                        Toast.makeText(SignUpActivity.this, "Both password are must be same", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        firebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    String uID = firebaseAuth.getUid();
                                    RegisterModel registerModel = new RegisterModel();
                                    registerModel.setUser_id(uID);
                                    registerModel.setUser_firstName(strFname);
                                    registerModel.setUser_email(strEmail);
                                    registerModel.setUser_mobileNumbr(strNum);
                                    SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("KEY_USERID", uID);
                                    editor.putString("KEY_PREF_EMAIL", strEmail);
                                    editor.putString("KEY_PREF_USERNAME", strFname);
                                    editor.putString("KEY_PREF_MOBILENUMBER", strNum);
                                    editor.remove("KEY_PREF_BG");
                                    editor.remove("KEY_PREF_ADDRESS");
                                    editor.remove("KEY_PREF_DOB");
                                    editor.remove("KEY_USERURL");

                                if (edtB2.isChecked())
                                {
                                    registerModel.setUser_Role(strB2);
                                    editor.putString("KEY_USERROLE",strB2);
                                    Intent i = new Intent(SignUpActivity.this, BottomNavActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                else
                                {
                                    registerModel.setUser_Role(strB1);
                                    editor.putString("KEY_USERROLE",strB1);
                                    Intent i = new Intent(SignUpActivity.this, BottomDocActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                    editor.commit();
                                    databaseReference.child(uID).setValue(registerModel);
                            }
                            }
                        });
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