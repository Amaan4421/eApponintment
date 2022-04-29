package com.smartapponintment.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;
import com.smartapponintment.models.DoctorModel;

public class AddDoctorFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    EditText edtName, edtDegree, edtEmail, edtHosname, edtNumber, edtSpeciality;
    Button add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_add_doctor, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Doctor");

        edtName = rootview.findViewById(R.id.t3);
        edtDegree = rootview.findViewById(R.id.t5);
        edtNumber = rootview.findViewById(R.id.t7);
        edtEmail = rootview.findViewById(R.id.t9);
        edtHosname = rootview.findViewById(R.id.t11);
        edtSpeciality = rootview.findViewById(R.id.t13);
        add = rootview.findViewById(R.id.addDoc);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Doctor Added Successfully!!", Toast.LENGTH_SHORT).show();

                String strName = edtName.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strSpeciality = edtSpeciality.getText().toString();
                String strNumber = edtNumber.getText().toString();
                String strHospname = edtHosname.getText().toString();
                String strDegree = edtDegree.getText().toString();
                String strId = databaseReference.push().getKey();

                DoctorModel doctorModel = new DoctorModel();
                doctorModel.setDoc_id(strId);
                doctorModel.setDoc_Name(strName);
                doctorModel.setDoc_Email(strEmail);
                doctorModel.setDoc_Speciality(strSpeciality);
                doctorModel.setDoc_Number(strNumber);
                doctorModel.setDoc_Hosp_name(strHospname);
                doctorModel.setDoc_Degree(strDegree);
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("KEY_PREF_DOCID", strId);
                editor.putString("KEY_PREF_DOCNAME", strName);
                editor.putString("KEY_PREF_DOCEMAIL", strEmail);
                editor.putString("KEY_PREF_DOCDEGREE", strDegree);
                editor.putString("KEY_PREF_DOCNUMBER", strNumber);
                editor.putString("KEY_PREF_DOCS", strSpeciality);
                editor.putString("KEY_PREF_DOCHOSPNAME", strHospname);
                editor.commit();
                databaseReference.child(strId).setValue(doctorModel);

                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragment = new AdminHomeFragment();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();

            }
        });
        return rootview;
    }
}