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
import com.smartapponintment.models.HospitalModel;


public class AddHospitalFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    EditText edtName,edtEmail,edtAddress,edtNumber;
    Button add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_add_hospital,container,false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Hospital");

        edtName = rootview.findViewById(R.id.h3);
        edtEmail = rootview.findViewById(R.id.t7);
        edtAddress = rootview.findViewById(R.id.t9);
        edtNumber = rootview.findViewById(R.id.t5);
        add = rootview.findViewById(R.id.addHospital);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String strName = edtName.getText().toString();
            String strEmail = edtEmail.getText().toString();
            String strAddress = edtAddress.getText().toString();
            String strNumber = edtNumber.getText().toString();
            String strId = databaseReference.push().getKey();

                if(strName.equals(""))
                {
                    Toast.makeText(getActivity(), "Name Required!!!", Toast.LENGTH_SHORT).show();
                }
                else if(strEmail.equals(""))
                {
                    Toast.makeText(getActivity(), "Email Required!!!", Toast.LENGTH_SHORT).show();
                }
                else if(strAddress.equals(""))
                {
                    Toast.makeText(getActivity(), "Hospital Address Required!!!", Toast.LENGTH_SHORT).show();
                }
                else if(strNumber.equals(""))
                {
                    Toast.makeText(getActivity(), "Number Required!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Hospital Added Successfully!!", Toast.LENGTH_SHORT).show();

                    HospitalModel hospitalModel = new HospitalModel();
                    hospitalModel.setHos_id(strId);
                    hospitalModel.setHos_Name(strName);
                    hospitalModel.setHos_Email(strEmail);
                    hospitalModel.setHos_Address(strAddress);
                    hospitalModel.setHos_Number(strNumber);
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KEY_PREF_HOSID", strId);
                    editor.putString("KEY_PREF_HOSNAME", strName);
                    editor.putString("KEY_PREF_HOSEMAIL", strEmail);
                    editor.putString("KEY_PREF_HOSADDRESS", strAddress);
                    editor.putString("KEY_PREF_HOSNUMBER", strNumber);
                    editor.commit();
                    databaseReference.child(strId).setValue(hospitalModel);

                    Fragment fragment = null;
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragment = new AdminHomeFragment();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                }
            }
        });
        return rootview;
    }
}