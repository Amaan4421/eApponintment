package com.smartapponintment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartapponintment.R;
import com.smartapponintment.adapters.DeleteDoctorAdapter;
import com.smartapponintment.models.DoctorModel;

import java.util.ArrayList;

public class DeleteDoctorFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ListView listView;
    private ArrayList<DoctorModel> doctorModelArrayList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    DeleteDoctorAdapter delDocAdapter;
    SearchView searchView;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_delete_doctor, container, false);

        searchView = (SearchView)rootview.findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        listView = rootview.findViewById(R.id.list_view);
        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Doctor");
        doctorModelArrayList = new ArrayList<DoctorModel>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren())
                {
                    DoctorModel doctorModel = dataSnapshot1.getValue(DoctorModel.class);
                    doctorModelArrayList.add(doctorModel);
                }
                delDocAdapter =new DeleteDoctorAdapter(getActivity(),doctorModelArrayList);
                listView.setAdapter(delDocAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return rootview;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        delDocAdapter.filter(text);
        return false;
    }
}