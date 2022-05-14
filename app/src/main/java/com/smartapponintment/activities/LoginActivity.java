package com.smartapponintment.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartapponintment.R;
import com.smartapponintment.models.RegisterModel;

public class LoginActivity extends AppCompatActivity {

    Button edtLogin;
    EditText edtEmail;
    EditText edtPassword;
    TextView newAcc;
    TextView tvfp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String emailAdmin = "admin27@gmail.com";
    String passAdmin = "admin273";

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Register");

        edtLogin = findViewById(R.id.btn_login);
        edtEmail = findViewById(R.id.edt_email2);
        edtPassword = findViewById(R.id.edt_password2);
        newAcc = findViewById(R.id.new_acc);
        tvfp = findViewById(R.id.tv_fp);

        tvfp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater)getLayoutInflater();
                View tvFp = layoutInflater.inflate(R.layout.raw_fp,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(tvFp);
                alertDialog.show();
                EditText edtFPEmail = tvFp.findViewById(R.id.edt_fp);
                Button btnCP = tvFp.findViewById(R.id.btn_submit);

                btnCP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String strFPEmail = edtFPEmail.getText().toString();
                        if(strFPEmail.equals(""))
                        {
                            Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(alertDialog.isShowing())
                            {
                                alertDialog.dismiss();
                            }
                            firebaseAuth.sendPasswordResetEmail(strFPEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(LoginActivity.this, "We have sent a password reset link to your email!!!", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(LoginActivity.this, "Please enter valid email!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
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

                if (strEmail.equals(""))
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
                else
                {
                        if (strEmail.equals(emailAdmin) && strPassword.equals(passAdmin))
                        {
                            Toast.makeText(LoginActivity.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("KEY_PREF_EMAIL",strEmail);
                            editor.commit();
                            Intent i = new Intent(LoginActivity.this, BottomAdminActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            firebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                        if(firebaseUser.isEmailVerified())
                                        {
                                            String strUID = firebaseAuth.getUid();
                                            databaseReference.child(strUID).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    RegisterModel registerModel = snapshot.getValue(RegisterModel.class);
                                                    String loginEmail = registerModel.getUser_email();
                                                    String loginName = registerModel.getUser_firstName();
                                                    String loginMobilenumber = registerModel.getUser_mobileNumbr();
                                                    String loginDob = registerModel.getUser_DOB();
                                                    String loginAddress = registerModel.getUser_Address();
                                                    String loginBG = registerModel.getUser_BG();
                                                    String loginUrl = registerModel.getUser_Url();
                                                    String loginRole = registerModel.getUser_Role();
                                                    SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment", MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString("KEY_USERID",strUID);
                                                    editor.putString("KEY_PREF_EMAIL", loginEmail);
                                                    editor.putString("KEY_PREF_USERNAME", loginName);
                                                    editor.putString("KEY_PREF_MOBILENUMBER", loginMobilenumber);
                                                    editor.putString("KEY_PREF_DOB", loginDob);
                                                    editor.putString("KEY_PREF_ADDRESS", loginAddress);
                                                    editor.putString("KEY_PREF_BG", loginBG);
                                                    editor.putString("KEY_USERURL",loginUrl);
                                                    editor.putString("KEY_USERROLE",loginRole);
                                                    editor.commit();

                                                    if (loginRole.equals("Patient"))
                                                    {
                                                        Toast.makeText(LoginActivity.this,"Welcome "+loginName,Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(LoginActivity.this, BottomNavActivity.class);
                                                        startActivity(i);
                                                        finish();
                                                    }
                                                    if (loginRole.equals("Doctor"))
                                                    {
                                                        Toast.makeText(LoginActivity.this,"Welcome "+loginName,Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(LoginActivity.this, BottomDocActivity.class);
                                                        startActivity(i);
                                                        finish();
                                                    }
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                }
                                            });
                                        }
                                        else
                                        {
                                            Toast.makeText(LoginActivity.this,"Your email is not verified yet!!!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(LoginActivity.this, "Wrong Credentials!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });//end of firebase auth
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

        public void onBackPressed()
        {
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
