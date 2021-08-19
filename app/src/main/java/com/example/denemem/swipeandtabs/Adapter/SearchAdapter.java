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

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView name, calory, gram;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        calory = (TextView)itemView.findViewById(R.id.calory);
        gram = (TextView)itemView.findViewById(R.id.gram);

    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>   {

    private Context context;
    private List<Food> foods;


    public SearchAdapter(Context context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_food, parent, false);


        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.name.setText(foods.get(position).getName());
        holder.calory.setText(foods.get(position).getCalory());
        holder.gram.setText(foods.get(position).getGram());
    }


    @Override
    public int getItemCount() {
        return foods.size();
    }
}
