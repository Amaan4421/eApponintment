package com.smartapponintment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartapponintment.R;
import com.smartapponintment.activities.AppointmentActivity;
import com.smartapponintment.models.AppointmentModel;

import java.util.ArrayList;

public class AppointmentAdapter extends BaseAdapter{

    Context context;
    ArrayList<AppointmentModel> appointmentModelArrayList;
    ArrayList<AppointmentModel> appointmentModelArrayListFiltered;

    public AppointmentAdapter(Context context, ArrayList<AppointmentModel> appointmentModelArrayList)
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
        convertView = layoutInflater.inflate(R.layout.raw_appointment_list, null);

        TextView docName = convertView.findViewById(R.id.tv_doc1);
        docName.setText(appointmentModelArrayList.get(position).getDoc_Name());
        TextView docNum = convertView.findViewById(R.id.tv_doc2);
        docNum.setText(appointmentModelArrayList.get(position).getDoc_Num());
        TextView pName = convertView.findViewById(R.id.tv_doc3);
        pName.setText(appointmentModelArrayList.get(position).getP_Name());
        TextView pNum = convertView.findViewById(R.id.tv_doc4);
        pNum.setText(appointmentModelArrayList.get(position).getP_Num());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strId = appointmentModelArrayList.get(position).getA_Id();
                String strPname = appointmentModelArrayList.get(position).getP_Name();
                String strDocname = appointmentModelArrayList.get(position).getDoc_Name();
                String strhospname = appointmentModelArrayList.get(position).getDoc_Hospname();
                String strAdate = appointmentModelArrayList.get(position).getP_Date();
                String strAtime = appointmentModelArrayList.get(position).getP_Time();

                Intent i = new Intent(context, AppointmentActivity.class);
                i.putExtra("KEY_ID",strId);
                i.putExtra("KEY_PNAME",strPname);
                i.putExtra("KEY_DOCNAME",strDocname);
                i.putExtra("KEY_HNAME",strhospname);
                i.putExtra("KEY_ADATE",strAdate);
                i.putExtra("KEY_ATIME",strAtime);
                context.startActivity(i);
            }
        });
        return convertView;
    }
}
