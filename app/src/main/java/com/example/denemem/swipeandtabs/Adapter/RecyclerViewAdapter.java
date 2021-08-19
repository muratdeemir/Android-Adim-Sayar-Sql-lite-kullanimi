package com.example.denemem.swipeandtabs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.denemem.swipeandtabs.Model.Food;
import com.example.denemem.swipeandtabs.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Food> mData;


    public RecyclerViewAdapter(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_food, parent, false);
        MyViewHolder vHolder= new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.name.setText(mData.get(position).getName());
        myViewHolder.calory.setText(mData.get(position).getCalory());
        myViewHolder.gram.setText(mData.get(position).getGram());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder   {

        public TextView name, calory, gram;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            calory = (TextView)itemView.findViewById(R.id.calory);
            gram = (TextView)itemView.findViewById(R.id.gram);

        }
    }
}
