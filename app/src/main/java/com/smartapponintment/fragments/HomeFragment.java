package com.smartapponintment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.smartapponintment.R;
import com.smartapponintment.adapters.ImageAdapter;

public class HomeFragment extends Fragment {

       Button doc1;
       Button doc2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_home,container,false);


        ViewPager mViewPager = rootview.findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);



        doc1 = rootview.findViewById(R.id.doc_1);
       doc2 = rootview.findViewById(R.id.doc_2);

       doc1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getActivity(),"You clicked first button",Toast.LENGTH_SHORT).show();
           }
       });

       doc2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getActivity(),"You clicked second button",Toast.LENGTH_SHORT).show();
           }
       });




        return rootview;
    }
}