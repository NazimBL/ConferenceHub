package com.example.dell.conferencehub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by DELL on 18/09/2017.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.RecyclerViewHolder>{
    String[] conf_names,conf_descriptions;
    Context context;

    public MyRecyclerAdapter(String[] names,String[] descriptions,Context context){
        this.conf_descriptions=descriptions;
        this.conf_names=names;
        this.context=context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        RecyclerViewHolder holder=new RecyclerViewHolder(view,this.context);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

            holder.text_desc.setText(conf_descriptions[position]);
            holder.text_names.setText(conf_names[position]);

    }

    @Override
    public int getItemCount() {
        return conf_names.length;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView text_names,text_desc;
        Context context;
        public RecyclerViewHolder(View view,Context c){

            super(view);
            this.context=c;

            view.setOnClickListener(this);
            text_names=(TextView)view.findViewById(R.id.conf_name_id);
            text_desc=(TextView)view.findViewById(R.id.conf_description_id);

        }

        @Override
        public void onClick(View view) {
            // int position=getAdapterPosition();
            //String description=this.conf[position];
            this.context.startActivity(new Intent(this.context,ConferenceView.class));
        }
    }
}
