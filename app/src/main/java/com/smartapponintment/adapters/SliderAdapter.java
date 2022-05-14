package com.smartapponintment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.smartapponintment.R;
import com.smartapponintment.models.SliderItem;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context context;
    private List<SliderItem> theSlideItemsModelClassList;

    public SliderAdapter(Context context, List<SliderItem> theSlideItemsModelClassList)
    {
        this.context = context;
        this.theSlideItemsModelClassList = theSlideItemsModelClassList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View sliderlayout = inflater.inflate(R.layout.item_layout,null);

        ImageView featured_image = sliderlayout.findViewById(R.id.my_image);

        featured_image.setImageResource(theSlideItemsModelClassList.get(position).getImgData());
        container.addView(sliderlayout);

        return sliderlayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return theSlideItemsModelClassList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
