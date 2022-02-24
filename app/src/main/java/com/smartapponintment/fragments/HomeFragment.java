package com.smartapponintment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.smartapponintment.R;
import com.smartapponintment.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

       Button doc1;
       Button doc2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_home,container,false);
       doc1 = rootview.findViewById(R.id.doc_1);
       doc2 = rootview.findViewById(R.id.doc_2);

       return rootview;
    }
}