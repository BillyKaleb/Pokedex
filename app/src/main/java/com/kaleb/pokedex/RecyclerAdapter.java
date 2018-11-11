package com.kaleb.pokedex;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.kaleb.pokedex.data.model.Result;
import com.kaleb.pokedex.main.Details.DetailsViewActivity;
import com.kaleb.pokedex.main.Pokemon.PokemonViewActivity;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Result> myList;

    public RecyclerAdapter(List<Result> myList) {
        this.myList = myList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(myList.get(position).getName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetailsViewActivity.class);
                myIntent.putExtra("url", myList.get(position).getName()); //Optional parameters
                v.getContext().startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void addList(List<Result> results){
        this.myList.addAll(results);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textView;
        RelativeLayout relativeLayout;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textRecycler);
            relativeLayout = itemView.findViewById(R.id.pokemon_recycle_layout);
        }
    }

    public void cleanList(){
        myList.clear();
    }
}
