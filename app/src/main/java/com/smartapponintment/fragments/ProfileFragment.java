package com.smartapponintment.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.smartapponintment.R;
import com.smartapponintment.activities.LoginActivity;
import com.smartapponintment.models.RegisterModel;

import java.util.Calendar;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

        Button submitChanges,changePassword;
        CircleImageView imageView;
        ImageView imageView2;
        EditText tvDate;
        EditText edtBg,edtAddress,edtDob;
        int date,month,year;

        FirebaseAuth firebaseAuth;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        FirebaseStorage firebaseStorage;
        FirebaseUser  firebaseUser;
        private String imageUrl;
        Bitmap bitmap;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                View rootView = inflater.inflate(R.layout.fragment_profile,container,false);

                firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
                databaseReference = firebaseDatabase.getReference("Register");
                firebaseStorage = FirebaseStorage.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                submitChanges = rootView.findViewById(R.id.save);
                changePassword = rootView.findViewById(R.id.CP);
                imageView = rootView.findViewById(R.id.imageView);
                imageView2 = rootView.findViewById(R.id.date);
                tvDate = rootView.findViewById(R.id.text4_1);
                edtBg = rootView.findViewById(R.id.text5_1);
                edtAddress = rootView.findViewById(R.id.text6_1);
                edtDob = rootView.findViewById(R.id.text4_1);

//                bitmap = rootView.findViewById(R.drawable.ic_baseline_account_circle);

                Calendar calendar =Calendar.getInstance();
                date = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");
                TextView tvEmail = rootView.findViewById(R.id.text);
                tvEmail.setText(strEmail);
                String strFname = sharedPreferences.getString("KEY_PREF_USERNAME", "");
                TextView tvFname = rootView.findViewById(R.id.text2_1);
                tvFname.setText(strFname);
                String strNum = sharedPreferences.getString("KEY_PREF_MOBILENUMBER", "");
                TextView tvNum = rootView.findViewById(R.id.text3_1);
                tvNum.setText(strNum);
                String strBG = sharedPreferences.getString("KEY_PREF_BG","");
                edtBg.setText(strBG);
                String strADD = sharedPreferences.getString("KEY_PREF_ADDRESS","");
                edtAddress.setText(strADD);
                String strDOB = sharedPreferences.getString("KEY_PREF_DOB","");
                edtDob.setText(strDOB);
                String strUrl = sharedPreferences.getString("KEY_USERURL","");
                if(strUrl.equals(""))
                {
                        imageView.setImageResource(R.drawable.ic_baseline_account_circle);
                }
                else
                {
                        Glide.with(getActivity()).load(strUrl).into(imageView);
                }

                imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                LayoutInflater layoutInflater = (LayoutInflater) getLayoutInflater();
                                View tvDp = inflater.inflate(R.layout.raw_dp,null);
                                ImageView tvCamera = tvDp.findViewById(R.id.img_camera);
                                ImageView tvGallery = tvDp.findViewById(R.id.img_gallery);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                AlertDialog alertDialog = builder.create();
                                alertDialog.setView(tvDp);
                                alertDialog.show();

                                tvGallery.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                                if (alertDialog.isShowing())
                                                {
                                                        alertDialog.dismiss();
                                                }
                                                Intent i = new Intent();
                                                i.setType("image/*");
                                                i.setAction(Intent.ACTION_PICK);
                                                startActivityForResult(i, 11);
                                        }
                                });

                                tvCamera.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                                if (alertDialog.isShowing())
                                                {
                                                        alertDialog.dismiss();
                                                }

                                                Intent i = new Intent();
                                                i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                                                startActivityForResult(i, 12);
                                        }
                                });
                        }
                });

                imageView2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                                tvDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                        }
                                }, year, month, date);
                                datePickerDialog.show();
                        }
                });

                submitChanges.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                Toast.makeText(getActivity(),"Saved",Toast.LENGTH_SHORT).show();

                                String strUId = sharedPreferences.getString("KEY_USERID","");
                                String strRole = sharedPreferences.getString("KEY_USERROLE","");
                                String strDob = edtDob.getText().toString();
                                String strBg = edtBg.getText().toString();
                                String strAddress = edtAddress.getText().toString();
                                RegisterModel registerModel = new RegisterModel();
                                registerModel.getUser_id(strUId);
                                registerModel.setUser_email(strEmail);
                                registerModel.setUser_mobileNumbr(strNum);
                                registerModel.setUser_firstName(strFname);
                                registerModel.setUser_Role(strRole);
                                registerModel.setUser_DOB(strDob);
                                registerModel.setUser_BG(strBg);
                                registerModel.setUser_Address(strAddress);
                                registerModel.setUser_Url(imageUrl);
                                editor.putString("KEY_PREF_BG",strBg);
                                editor.putString("KEY_PREF_ADDRESS",strAddress);
                                editor.putString("KEY_PREF_DOB",strDob);
                                editor.putString("KEY_USERURL",imageUrl);
                                editor.commit();
                                databaseReference.child(strUId).setValue(registerModel);
                        }
                });

                changePassword.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                LayoutInflater layoutInflater = (LayoutInflater) getLayoutInflater();
                                View tvFp = layoutInflater.inflate(R.layout.raw_fp,null);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                                                        Toast.makeText(getActivity(),"Enter email",Toast.LENGTH_SHORT).show();
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
                                                                                Intent i = new Intent(getActivity(), LoginActivity.class);
                                                                                startActivity(i);
                                                                                Toast.makeText(getActivity(), "We have sent an email to your email id!!!", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        else
                                                                        {
                                                                                Toast.makeText(getActivity(), "Server error!!!", Toast.LENGTH_SHORT).show();
                                                                        }

                                                                }
                                                        });
                                                }
                                        }
                                });
                        }
                });
                return rootView;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(requestCode == 11 )
                {
                        Uri uri = data.getData();
                        imageView.setImageURI(uri);
                        uploadFile(uri);
                }
                if(requestCode == 12)
                {
                        assert data != null;
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(bitmap);
                }
        }

        private void uploadFile(Uri uri)
        {
                if(uri != null)
                {
                        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setTitle("Uploading...");
                        progressDialog.show();
                        StorageReference storageReference =firebaseStorage.getReference().child("images/"+ UUID.randomUUID().toString()+".png");
                        storageReference.putFile(uri)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getActivity().getApplicationContext(),"Uploaded!!!",Toast.LENGTH_LONG).show();
                                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri downloadUri)
                                                        {
                                                                imageUrl = downloadUri.toString();
                                                                Log.e("TAG","onSuccess: "+imageUrl);
                                                        }
                                                });
                                        }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getActivity().getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                })
                                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                                                double progress =(100.0 * taskSnapshot.getBytesTransferred())/ taskSnapshot.getTotalByteCount();
                                                progressDialog.setMessage("Uploading " + ((int)progress) + "%....");
                                        }
                                });
                }
                else
                {
                        Toast.makeText(getActivity(),"No picture selected...",Toast.LENGTH_SHORT).show();
                }
        }
}
