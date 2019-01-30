package com.berbagibagi.ruangrapat;


import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.ruangR.setText(listItem.getRuang());
        holder.namaR.setText(listItem.getNama());
        holder.tanggalR.setText(listItem.getTanggal());
        holder.waktuR.setText(listItem.getWaktu());
        holder.hasilR.setText(listItem.getHasil());
        holder.perihalR.setText(listItem.getPerihal());
        holder.notulisR.setText(listItem.getNotulis());

        if (!listItem.getTampungHasil().equals("null")){
            holder.hasil.setVisibility(View.VISIBLE);
            holder.hasil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = "http://ruangrapat.000webhostapp.com/assets/hasil/"+listItem.getHasil();
                    Uri uri = Uri.parse(value);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ruangR,namaR,tanggalR,waktuR,hasilR,perihalR,notulisR;
        public Button hasil;

        public ViewHolder(View itemView) {
            super(itemView);

            ruangR = (TextView) itemView.findViewById(R.id.ruangR);
            namaR = (TextView) itemView.findViewById(R.id.namaR);
            tanggalR = (TextView) itemView.findViewById(R.id.tanggalR);
            waktuR = (TextView) itemView.findViewById(R.id.waktuR);
            hasilR = (TextView) itemView.findViewById(R.id.hasilR);
            perihalR = (TextView) itemView.findViewById(R.id.perihalR);
            notulisR = (TextView) itemView.findViewById(R.id.notulisR);

            hasil = (Button) itemView.findViewById(R.id.lihatRapat);
        }
    }
}