package com.smartapponintment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartapponintment.R;
import com.smartapponintment.activities.BookDoctorActivity;
import com.smartapponintment.models.DoctorModel;

import java.util.ArrayList;
import java.util.Locale;

public class DoctorAdapter extends BaseAdapter{

    Context context;
    ArrayList<DoctorModel> doctorModelArrayList;
    ArrayList<DoctorModel> doctorModelArrayListFiltered;

    public DoctorAdapter(Context context, ArrayList<DoctorModel> doctorModelArrayList)
    {
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

        TextView docName = convertView.findViewById(R.id.tv_doc1);
        docName.setText(doctorModelArrayList.get(position).getDoc_Name());
        TextView docHospname = convertView.findViewById(R.id.tv_doc2);
        docHospname.setText(doctorModelArrayList.get(position).getDoc_Hosp_name());
        TextView docSp = convertView.findViewById(R.id.tv_doc3);
        docSp.setText(doctorModelArrayList.get(position).getDoc_Speciality());
        TextView docDegree = convertView.findViewById(R.id.tv_doc4);
        docDegree.setText(doctorModelArrayList.get(position).getDoc_Degree());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strId = doctorModelArrayList.get(position).getDoc_id();
                String docName = doctorModelArrayList.get(position).getDoc_Name();
                String docHospital = doctorModelArrayList.get(position).getDoc_Hosp_name();
                String docSpeciality = doctorModelArrayList.get(position).getDoc_Speciality();
                String docDegree = doctorModelArrayList.get(position).getDoc_Degree();
                String docNumber = doctorModelArrayList.get(position).getDoc_Number();
                String docEmail = doctorModelArrayList.get(position).getDoc_Email();

                Intent i = new Intent(context, BookDoctorActivity.class);
                i.putExtra("KEY_ID",strId);
                i.putExtra("KEY_NAME",docName);
                i.putExtra("KEY_DOCHOSPITAL",docHospital);
                i.putExtra("KEY_DOCSP",docSpeciality);
                i.putExtra("KEY_DOCDEGREE",docDegree);
                i.putExtra("KEY_DOCNUMBER",docNumber);
                i.putExtra("KEY_DOCEMAIL",docEmail);
                context.startActivity(i);
            }
        });
        return convertView;
    }

    public void filter(String text) {
        text = text.toLowerCase(Locale.getDefault());
        doctorModelArrayList.clear();
        if(text.length() == 0)
        {
            doctorModelArrayList.addAll(doctorModelArrayListFiltered);

        }
        else
        {
            for(DoctorModel d : doctorModelArrayListFiltered)
            {
                if(d.getDoc_Name().toLowerCase(Locale.getDefault()).contains(text))
                {
                    doctorModelArrayList.add(d);
                }
            }
        }
        notifyDataSetChanged();
    }
}
