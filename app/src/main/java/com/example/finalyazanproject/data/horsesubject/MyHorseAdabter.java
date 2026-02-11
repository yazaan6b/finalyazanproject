package com.example.finalyazanproject.data.horsesubject;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.finalyazanproject.Maindetail;
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
        name.setText(current.getName()+"");
        age.setText(current.getAge()+"");


        // Set click listener on the item view
        vitem.setOnClickListener(v -> {
            Horse horse = getItem(position);
            if (horse != null) {
                Intent intent = new Intent(v.getContext(), Maindetail.class);
                intent.putExtra("horse_data",  horse);
                v.getContext().startActivity(intent);
            }
        });


        return vitem;
    }

    }