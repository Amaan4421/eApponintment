package com.smartapponintment.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.smartapponintment.R;
import com.smartapponintment.fragments.HomeDocFragment;

public class ImageAdapter2 extends PagerAdapter {

        HomeDocFragment cContext;

    public ImageAdapter2(HomeDocFragment homeDocFragment) {
        this.cContext = homeDocFragment;
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
            ImageView imageView = new ImageView(cContext.getContext());
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
