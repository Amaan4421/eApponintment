package com.smartapponintment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartapponintment.R;
import com.smartapponintment.activities.ShowAppointmentActivity;
import com.smartapponintment.adapters.DoctorAdapter;
import com.smartapponintment.models.DoctorModel;

import java.util.ArrayList;

public class BookApointmentFragment extends Fragment {

    private ListView listView;
    private ArrayList<DoctorModel> doctorModelArrayList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    DoctorAdapter docAdapter;
    SearchView searchView;
    TextView tvShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_book_apointment,container,false);

        searchView = rootview.findViewById(R.id.search);
//        searchView.setOnQueryTextListener(this);

        tvShow = rootview.findViewById(R.id.show);
        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ShowAppointmentActivity.class);
                startActivity(i);

            }
        });

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
                docAdapter =new DoctorAdapter(getActivity(),doctorModelArrayList);
                listView.setAdapter(docAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return rootview;
    }
//    @Override
//    public boolean onQueryTextSubmit(String s) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String s) {
//        String  text = s;
//        docAdapter.filter(text);
//        return false;
//    }
}
