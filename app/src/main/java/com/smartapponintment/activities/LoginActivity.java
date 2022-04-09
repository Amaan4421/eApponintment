package com.smartapponintment.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    RadioGroup radioGroup;
    RadioButton edtB1;
    RadioButton edtB2;
    RadioButton edtB3;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String  passwordPattern = "[A-Za-z]+[0-9]+";
    String emailAdmin = "admin27@gmail.com";
    String passAdmin = "admin273";

    private FirebaseAuth firebaseAuth;
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
        radioGroup = findViewById(R.id.tv_rg);
        edtB1 = findViewById(R.id.rb1);
        edtB2 = findViewById(R.id.rb2);
        edtB3 = findViewById(R.id.rb3);

        tvfp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String strEmail = edtEmail.getText().toString();
                if (strEmail.equals("")) {
                    edtEmail.setError("Enter the email first");
                } else {
                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View fpView = layoutInflater.inflate(R.layout.raw_fp, null);
                    EditText edtFp = fpView.findViewById(R.id.edt_fp);
                    EditText edtFp2 = fpView.findViewById(R.id.edt_fp2);
                    Button btnSubmit = fpView.findViewById(R.id.btn_submit);
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setView(fpView);
                    alertDialog.show();
                    String strFp = edtFp.getText().toString();
                    String strFp2 = edtFp2.getText().toString();
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (strFp.matches(passwordPattern)) {
                                Toast.makeText(LoginActivity.this, "Password must contain atleast one number", Toast.LENGTH_SHORT).show();
                            } else if (!strFp.matches(strFp2)) {
                                Toast.makeText(LoginActivity.this, "Both passwords are must be same", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Password changed!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
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

                if (!edtB1.isChecked() && !edtB2.isChecked() && !edtB3.isChecked()) {
                    Toast.makeText(LoginActivity.this, "Select Doctor or Patient or Admin", Toast.LENGTH_SHORT).show();
                } else if (strEmail.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Email id", Toast.LENGTH_SHORT).show();
                } else if (!strEmail.matches(emailPattern)) {
                    Toast.makeText(LoginActivity.this, "Enter valid Email id", Toast.LENGTH_SHORT).show();
                } else if (strPassword.equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (edtB1.isChecked()) {
                        if (strEmail.equals(emailAdmin) && strPassword.equals(passAdmin)) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, BottomAdminActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Enter Valid email or password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        firebaseAuth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
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
                                            String loginPassword = registerModel.getUser_password();
                                            SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("KEY_USERID",strUID);
                                            editor.putString("KEY_PREF_EMAIL", loginEmail);
                                            editor.putString("KEY_PREF_USERNAME", loginName);
                                            editor.putString("KEY_PREF_MOBILENUMBER", loginMobilenumber);
                                            editor.putString("KEY_PREF_DOB", loginDob);
                                            editor.putString("KEY_PREF_ADDRESS", loginAddress);
                                            editor.putString("KEY_PREF_BG", loginBG);
                                            editor.putString("KEY_PREF_PASSWORD", loginPassword);
                                            editor.commit();

                                            if (edtB3.isChecked()) {
                                                Intent i = new Intent(LoginActivity.this, BottomNavActivity.class);
                                                startActivity(i);
                                                finish();
                                            } else {
                                                Intent i = new Intent(LoginActivity.this, BottomDocActivity.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                                } else {
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
