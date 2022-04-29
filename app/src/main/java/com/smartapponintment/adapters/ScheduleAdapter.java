package com.smartapponintment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartapponintment.R;
import com.smartapponintment.activities.PatientDetailsActivity;
import com.smartapponintment.models.AppointmentModel;

import java.util.ArrayList;

public class ScheduleAdapter extends BaseAdapter{

    Context context;
    ArrayList<AppointmentModel> appointmentModelArrayList;
    ArrayList<AppointmentModel> appointmentModelArrayListFiltered;

    public ScheduleAdapter(Context context, ArrayList<AppointmentModel> appointmentModelArrayList)
    {
        this.context = context;
        this.appointmentModelArrayList = appointmentModelArrayList;
        this.appointmentModelArrayListFiltered = new ArrayList<AppointmentModel>();
        this.appointmentModelArrayListFiltered.addAll(appointmentModelArrayList);
    }

    @Override
    public int getCount() {
        return appointmentModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return appointmentModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.raw_schedule_list, null);

        TextView pName = convertView.findViewById(R.id.tv_doc1);
        pName.setText(appointmentModelArrayList.get(position).getP_Name());
        TextView pNum = convertView.findViewById(R.id.tv_doc2);
        pNum.setText(appointmentModelArrayList.get(position).getP_Num());
        TextView aDate = convertView.findViewById(R.id.tv_doc3);
        aDate.setText(appointmentModelArrayList.get(position).getP_Date());
        TextView aTime = convertView.findViewById(R.id.tv_doc4);
        aTime.setText(appointmentModelArrayList.get(position).getP_Time());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strId = appointmentModelArrayList.get(position).getA_Id();
                String strPname = appointmentModelArrayList.get(position).getP_Name();
                String strDname = appointmentModelArrayList.get(position).getDoc_Name();
                String strHospname = appointmentModelArrayList.get(position).getDoc_Hospname();
                String strPnum = appointmentModelArrayList.get(position).getP_Num();
                String strAdate = appointmentModelArrayList.get(position).getP_Date();
                String strAtime = appointmentModelArrayList.get(position).getP_Time();

                Intent i = new Intent(context, PatientDetailsActivity.class);
                i.putExtra("S_ID",strId);
                i.putExtra("S_PNMAE",strPname);
                i.putExtra("S_DNAME",strDname);
                i.putExtra("S_HOSPNAME",strHospname);
                i.putExtra("S_PNUM",strPnum);
                i.putExtra("S_ADATE",strAdate);
                i.putExtra("S_ATIME",strAtime);
                context.startActivity(i);
            }
        });
        return convertView;
    }
}
