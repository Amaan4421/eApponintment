package com.smartapponintment.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.smartapponintment.R;
import com.smartapponintment.fragments.HomeFragment;

public class ImageAdapter extends PagerAdapter {

    HomeFragment mContext;

    public ImageAdapter(HomeFragment context) {
        this.mContext = context;
    }

    @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        private int[] sliderImageId = new int[]{
                R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(sliderImageId[position]);
            ((ViewPager) container).addView(imageView, 0);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            ((ViewPager) container).removeView((ImageView) object);
        }

        @Override
        public int getCount()
        {
            return sliderImageId.length;
        }

}
