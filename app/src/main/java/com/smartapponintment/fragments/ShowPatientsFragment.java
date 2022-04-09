package com.smartapponintment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smartapponintment.R;


public class ShowPatientsFragment extends Fragment {
//
//        private ListView listView;
//        private ArrayList<DoctorModel> doctorModelArrayList;
//        private FirebaseDatabase firebaseDatabase;
//        private DatabaseReference databaseReference;
//        DoctorAdapter bookAdapter;
//        SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_show_users,container,false);
//        listView = rootview.findViewById(R.id.list_view);
//
//        doctorModelArrayList = new ArrayList<DoctorModel>();
//        firebaseDatabase = FirebaseDatabase.getInstance("https://eappointment-b69f7-default-rtdb.asia-southeast1.firebasedatabase.app/");

        return rootview;
    }


    //        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_cat_display);
//
//            listView = findViewById(R.id.list_view);
//            searchView = (SearchView) findViewById(R.id.search);
//            searchView.setOnQueryTextListener(this);
//
//            categoryModelArrayList = new ArrayList<CategoryModel>();
//            firebaseDatabase = FirebaseDatabase.getInstance("https://myappinternship-default-rtdb.asia-southeast1.firebasedatabase.app/");
//            databaseReference = firebaseDatabase.getReference("Category");
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//
//                        CategoryModel categoryModel = dataSnapshot1.getValue(CategoryModel.class);
//                        categoryModelArrayList.add(categoryModel);
//                    }
//
//
//                    bookAdapter = new CategoryAdapter(CatDisplayActivity.this,categoryModelArrayList);
//                    listView.setAdapter(bookAdapter);
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//
//        }
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//
//            return false;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            String text = newText;
//            bookAdapter.filter(text);
//            return false;
//        }

}