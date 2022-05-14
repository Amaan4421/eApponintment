package com.smartapponintment.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.smartapponintment.R;
import com.smartapponintment.adapters.SliderAdapter;
import com.smartapponintment.models.SliderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class HomeDocFragment extends Fragment {

    private List<SliderItem> listItems;
    private ViewPager pager;

    int imgData[] = {R.drawable.img_1,R.drawable.img_2,R.drawable.img_3};
    int i;

    private ArrayList<SliderItem> sliderItemArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_home_doc, container, false);

        pager = rootview.findViewById(R.id.viewPage);
        listItems = new ArrayList<SliderItem>();

        for(i = 0; imgData.length > i; i++)
        {
            SliderItem sliderItem = new SliderItem(imgData[i]);
            listItems.add(sliderItem);
        }

        SliderAdapter sliderAdapter = new SliderAdapter(getActivity(),listItems);
        pager.setAdapter(sliderAdapter);

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new slider_timer(),2000,3000);

//        ViewPager mViewPager = rootview.findViewById(R.id.viewPage);
//        ImageAdapter2 adapterView = new ImageAdapter2(this);
//        mViewPager.setAdapter(adapterView);

        return rootview;
    }

    private class slider_timer extends TimerTask {
        @Override
        public void run() {
            Activity activity = getActivity();
            if(activity != null)
            {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(pager.getCurrentItem() < listItems.size()-1)
                        {
                            pager.setCurrentItem(pager.getCurrentItem()+1);
                        }
                        else
                            pager.setCurrentItem(0);
                    }
                });
            }
        }
    }
}
