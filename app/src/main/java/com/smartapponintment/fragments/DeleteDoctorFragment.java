package com.smartapponintment.fragments;

import android.content.Context;
import android.content.Intent;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smartapponintment.R;

public class DeleteDoctorFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
//    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    Button btnDelete;
    EditText edtDoc,edtHosp;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_delete_doctor,container,false);

        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Doctor");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String strId = sharedPreferences.getString("KEY_PREF_DOCID","");

        Intent i = getActivity().getIntent();


//        edtDoc = rootview.findViewById(R.id.e2);
//        edtHosp = rootview.findViewById(R.id.e1);
        btnDelete = rootview.findViewById(R.id.deleteButton);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Doctor deleted!!",Toast.LENGTH_SHORT).show();
                databaseReference.child(strId).removeValue();

                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragment = new AdminHomeFragment();
                fragmentTransaction.replace(R.id.frame,fragment);
                fragmentTransaction.commit();

            }
        });

        return rootview;
    }
}