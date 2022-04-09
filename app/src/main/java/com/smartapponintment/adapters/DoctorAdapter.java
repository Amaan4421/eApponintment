package com.smartapponintment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartapponintment.R;
import com.smartapponintment.fragments.AdminHomeFragment;
import com.smartapponintment.models.DoctorModel;

import java.util.ArrayList;

public class DoctorAdapter extends BaseAdapter{
    Context context;
    ArrayList<DoctorModel> doctorModelArrayList;
    ArrayList<DoctorModel> doctorModelArrayListFiltered;

    public DoctorAdapter(Context context, ArrayList<DoctorModel> categoryModelArrayList) {

        this.context = context;
        this.doctorModelArrayList = doctorModelArrayList;
        this.doctorModelArrayListFiltered = new ArrayList<DoctorModel>();
        this.doctorModelArrayListFiltered.addAll(doctorModelArrayList);

    }

    @Override
    public int getCount() {
        return doctorModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.raw_list, null);

//        CircleImageView imgBook = convertView.findViewById(R.id.img_book);
//        String url = doctorModelArrayList.get(position).getCat_url();
//         Glide.with(context).load(url).into(imgBook);
        TextView tvBook = convertView.findViewById(R.id.tv_book);
        //imgBook.setImageResource(categoryModelArrayList.get(position).getImgBook());
        tvBook.setText(doctorModelArrayList.get(position).getDoc_Name());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = categoryModelArrayList.get(position).getCat_url();
                String strId = doctorModelArrayList.get(position).getDoc_id();
                String docName = doctorModelArrayList.get(position).getDoc_Name();
                String docHospital = doctorModelArrayList.get(position).getDoc_Hosp_name();
                String docSpeciality = doctorModelArrayList.get(position).getDoc_Speciality();

                Intent i = new Intent(context, AdminHomeFragment.class);
                i.putExtra("KEY_ID",strId);
                i.putExtra("KEY_NAME",docName);
                i.putExtra("KEY_DOCHOSPITAL",docHospital);
                i.putExtra("KEY_DOCSP",docSpeciality);
                context.startActivity(i);
            }
        });
        return convertView;
    }
//    // Filter Class
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        doctorModelArrayList.clear();
//        if (charText.length() == 0) {
//            doctorModelArrayList.addAll(doctorModelArrayListFiltered);
//        } else {
//            for (DoctorModel wp : doctorModelArrayListFiltered) {
//                if (wp.getDoc_Name().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    doctorModelArrayList.add(wp);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
}
