package com.example.finalyazanproject.data.horsesubject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.finalyazanproject.R;
import com.example.finalyazanproject.data.mytaskplace.Mytask;

public class MyHorseAdabter extends ArrayAdapter<Horse> {
    private final int itemLayout;

    public MyHorseAdabter(@NonNull Context context, int resource) {
        super(context, resource);
        this.itemLayout =resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vitem= convertView;
        if(vitem==null)
            vitem= LayoutInflater.from(getContext()).inflate(R.layout.horseitem,parent,false);
        ImageView img=vitem.findViewById(R.id.itmiimage);
        TextView name=vitem.findViewById(R.id.itmtvname1);
        TextView age=vitem.findViewById(R.id.itmtveng);
        TextView available=vitem.findViewById(R.id.itmtvavilable);



        Horse current=getItem(position);
        name.setText(current.getName());
        age.setText(current.getAge());
        available.setText(current.getAvilable());


        return vitem;

        }

    }