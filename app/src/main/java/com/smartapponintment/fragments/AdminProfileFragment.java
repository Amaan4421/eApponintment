package com.smartapponintment.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.smartapponintment.R;
import com.smartapponintment.activities.LoginActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminProfileFragment extends Fragment {

    Button btnLogout;
    CircleImageView imageView;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_admin_profile,container,false);
        btnLogout = rootview.findViewById(R.id.btn_logout);
        imageView = rootview.findViewById(R.id.imageView);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("e_Appointment", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("KEY_PREF_EMAIL");
                editor.remove("KEY_PREF_Password");
                editor.commit();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getLayoutInflater();
                View tvDp = inflater.inflate(R.layout.raw_dp,null);
                ImageView tvCamera = tvDp.findViewById(R.id.img_camera);
                ImageView tvGallery = tvDp.findViewById(R.id.img_gallery);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(tvDp);
                alertDialog.show();

                tvGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (alertDialog.isShowing())
                        {
                            alertDialog.dismiss();
                        }
                        Intent j = new Intent();
                        j.setType("image/*");
                        j.setAction(Intent.ACTION_PICK);
                        startActivityForResult(j, 11);
                    }
                });

                tvCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (alertDialog.isShowing())
                        {
                            alertDialog.dismiss();
                        }

                        Intent i = new Intent();
                        i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i, 12);
                    }
                });

            }

        });
        return rootview;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 11){
            assert data != null;
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
        if(requestCode == 12){
            assert data != null;
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}