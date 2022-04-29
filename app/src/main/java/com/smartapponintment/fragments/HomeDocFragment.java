package com.smartapponintment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.smartapponintment.R;
import com.smartapponintment.adapters.ImageAdapter2;

public class HomeDocFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_home_doc, container, false);

        ViewPager mViewPager = rootview.findViewById(R.id.viewPage);
        ImageAdapter2 adapterView = new ImageAdapter2(this);
        mViewPager.setAdapter(adapterView);

        return rootview;
    }
}
